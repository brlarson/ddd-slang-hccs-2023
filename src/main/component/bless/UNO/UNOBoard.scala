// UNOBoard.scala
// provide hardware-specific drivers for an Arduino UNO board
// accessed through Firmata4j
package bless.UNO

import org.

firmata4j._
import org.firmata4j.firmata._
import org.sireum._

object UNOBoard {
  // change this string value to the port name in the lower-right corner
  // of the Arduino IDE with which StandardFirmata.ino was loaded onto the UNO board
  val port: String = "/dev/cu.usbserial-14310"

  val ap_pin = 12 //to LED indicating atrial pace
  val as_pin = 11 //from push-button indicating atrial sense
  val vs_pin = 9 //to LED indicating ventricular sense
  val vp_pin = 10 //from push-button indicating ventricular sense

  private var device: IODevice = _
  private var initRun: B = F
  private var apPin: Pin = _
  private var asPin: Pin = _
  private var vpPin: Pin = _
  private var vsPin: Pin = _
  private val start_time = System.currentTimeMillis()
  private val debounceTime: Long = 50 //ms
  private var lastVS : Long = System.currentTimeMillis()
  private var lastAS : Long = System.currentTimeMillis()

  def init(): Unit = {
    this.synchronized {
      try {
        if (!initRun) {
          initRun = T
          println("creating FirmataDevice")
          device = new FirmataDevice(port.native)

          println("starting FirmataDevice")
          device.start()
          device.ensureInitializationIsDone()
          println("FirmataDevice initialization is done")

          apPin = device.getPin(ap_pin)
          asPin = device.getPin(as_pin)
          vpPin = device.getPin(vp_pin)
          vsPin = device.getPin(vs_pin)

          apPin.setMode(Pin.Mode.OUTPUT)
          asPin.setMode(Pin.Mode.INPUT)
          vpPin.setMode(Pin.Mode.OUTPUT)
          vsPin.setMode(Pin.Mode.INPUT)

          println("adding event listeners")
          vsPin.addEventListener(new PinEventListener() {
            def onValueChange(ioEvent: IOEvent): Unit = {
              if (System.currentTimeMillis() - lastVS > debounceTime) //debounce
                if (ioEvent.getValue == 0) {
                  //send "sense" event from device FrontEnd's sense port
                  UNOBridge.putVS(bless.Arch.Pacemaker_imp_Instance_fe.operational_api)
                  println("V sense @ " + (System.currentTimeMillis() - start_time) + " ms")
                  lastVS = System.currentTimeMillis()
                }
                else lastVS = System.currentTimeMillis()
            }

            def onModeChange(ioEvent: IOEvent): Unit = {}
          }: PinEventListener)
          //blink the LED
          vpOut()

          asPin.addEventListener(new PinEventListener() {
            def onValueChange(ioEvent: IOEvent): Unit = {
              if (System.currentTimeMillis() - lastAS > debounceTime) //debounce
                if (ioEvent.getValue == 0) {
                  //send "sense" event from device FrontEnd's sense port
                  UNOBridge.putAS(bless.Arch.Pacemaker_imp_Instance_fe.operational_api)
                  println("A sense @ " + (System.currentTimeMillis() - start_time) + " ms")
                  lastAS = System.currentTimeMillis()
                }
                else lastAS = System.currentTimeMillis()
            }

            def onModeChange(ioEvent: IOEvent): Unit = {}
          }: PinEventListener)
          //blink the LED
          apOut()
        }
      } catch {
        case e: Exception =>
          cprintln(T, e)
      }
    }
  }  //end of init

  def vpOut(): Unit = {
    if (vpPin != null) {
      vpPin.setValue(1)
      println("V pace @ " + (System.currentTimeMillis() - start_time) + " ms")
      Thread.sleep(100)
      vpPin.setValue(0)
    }
    else
      println("vpOut not sent because vpPin not initialized")
  }

  def apOut(): Unit = {
    if (apPin != null) {
      apPin.setValue(1)
      println("A pace @ " + (System.currentTimeMillis() - start_time) + " ms")
      Thread.sleep(100)
      apPin.setValue(0)
    }
    else
      println("apOut not sent because apPin not initialized")
  }

  //  def analogRead(pin: Z, mode: PinMode.Type): Z = {
//    // for now just let exception halt the program
//    val p = device.getPin(pin.toInt)
//    p.setMode(Pin.Mode.valueOf(mode.name.native))
//    return Z(p.getValue)
//  }
//
//  def analogWrite(pin: Z, mode: PinMode.Type, value: Z): Unit = {
//    // for now just let exception halt the program
//    val p = device.getPin(pin.toInt)
//    p.setMode(Pin.Mode.valueOf(mode.name.native))
//    p.setValue(value.toInt)
//  }

  def stop(): Unit = {
    device.stop()
  }

}
