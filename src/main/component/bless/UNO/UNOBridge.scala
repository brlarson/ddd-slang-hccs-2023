// UNOBridge.scala
package bless.UNO

import art.Art
import bless.PG.{FrontEnd_Initialization_Api, FrontEnd_Operational_Api}
import org.sireum.{IS, _}
//import bless.UNO.FirmataUtil.PinMode

object UNOBridge {
  // change this string value to the port name in the lower-right corner
  // of the Arduino IDE with which StandardFirmata.ino was loaded onto the UNO board
  val port: String = "/dev/cu.usbserial-14220"

  val apPin: Z = 12  //to LED indicating atrial pace
  val asPin: Z = 11  //from push-button indicating atrial sense
  val vsPin: Z = 10  //to LED indicating ventricular sense
  val vpPin: Z = 9   //from push-button indicating ventricular sense

  def init(api: FrontEnd_Initialization_Api): Unit = {
    UNOBoard.init()
  }

  def putVS(api: FrontEnd_Operational_Api): Unit = {
    api.logDebug("putVS")
    api.put_v()
    Art.sendOutput(IS(api.v_Id), IS())
  }

  def putVP(api: FrontEnd_Operational_Api): Unit = {
    api.logDebug("putVP")
    UNOBoard.vpOut()
  }

  def putAS(api: FrontEnd_Operational_Api): Unit = {
    api.logDebug("putAS")
    api.put_a()
    Art.sendOutput(IS(api.a_Id), IS())
  }

  def putAP(api: FrontEnd_Operational_Api): Unit = {
    api.logDebug("putAP")
    UNOBoard.apOut()
  }

  def stop(): Unit = {
    UNOBoard.stop()
  }

}
