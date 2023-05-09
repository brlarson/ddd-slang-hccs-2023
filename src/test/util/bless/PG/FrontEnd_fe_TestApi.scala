package bless.PG

import org.sireum._
import art.{ArtNative_Ext, Empty}
import bless._

// This file was auto-generated.  Do not edit
abstract class FrontEnd_fe_TestApi extends BridgeTestSuite[FrontEnd_fe_Bridge](Arch.Pacemaker_imp_Instance_fe) {

  /** helper function to set the values of all input ports.
   * @param ap the number of events to place in the ap event port queue.
   *   ART currently supports single element event queues so at most
   *   one event will be placed in the queue.
   * @param vp the number of events to place in the vp event port queue.
   *   ART currently supports single element event queues so at most
   *   one event will be placed in the queue.
   */
  def put_concrete_inputs(ap : Z,
                          vp : Z): Unit = {
    for(i <- 0 until ap) {
      put_ap()
    }
    for(i <- 0 until vp) {
      put_vp()
    }
  }


  /** helper function to check FrontEnd_fe's
   * output ports.  Use named arguments to check subsets of the output ports.
   * @param a method that will be called with the number of events to be sent
   *        on the outgoing event port 'a'.
   * @param v method that will be called with the number of events to be sent
   *        on the outgoing event port 'v'.
   */
  def check_concrete_output(a: Z => B = aParam => {T},
                            v: Z => B = vParam => {T}): Unit = {
    var testFailures: ISZ[ST] = ISZ()

    // TODO: event port getter should return the number of events in
    //       the output queue when queue sizes > 1 support is added to ART
    val aValue: Z = if(get_a().nonEmpty) z"1" else z"0"
    if(!a(aValue)) {
      testFailures = testFailures :+ st"'a' did not match expected: ${aValue} events were in the outgoing event queue"
    }
    // TODO: event port getter should return the number of events in
    //       the output queue when queue sizes > 1 support is added to ART
    val vValue: Z = if(get_v().nonEmpty) z"1" else z"0"
    if(!v(vValue)) {
      testFailures = testFailures :+ st"'v' did not match expected: ${vValue} events were in the outgoing event queue"
    }

    assert(testFailures.isEmpty, st"${(testFailures, "\n")}".render)
  }


  // setter for in EventPort
  def put_ap(): Unit = {
    ArtNative_Ext.insertInPortValue(bridge.operational_api.ap_Id, Empty())
  }

  // setter for in EventPort
  def put_vp(): Unit = {
    ArtNative_Ext.insertInPortValue(bridge.operational_api.vp_Id, Empty())
  }

  // getter for out EventPort
  def get_a(): Option[art.Empty] = {
    val value: Option[art.Empty] = get_a_payload() match {
      case Some(Empty()) => Some(Empty())
      case Some(v) => fail(s"Unexpected payload on port a.  Expecting 'Empty' but received ${v}")
      case _ => None[art.Empty]()
    }
    return value
  }

  // payload getter for out EventPort
  def get_a_payload(): Option[Empty] = {
    return ArtNative_Ext.observeOutPortValue(bridge.initialization_api.a_Id).asInstanceOf[Option[Empty]]
  }

  // getter for out EventPort
  def get_v(): Option[art.Empty] = {
    val value: Option[art.Empty] = get_v_payload() match {
      case Some(Empty()) => Some(Empty())
      case Some(v) => fail(s"Unexpected payload on port v.  Expecting 'Empty' but received ${v}")
      case _ => None[art.Empty]()
    }
    return value
  }

  // payload getter for out EventPort
  def get_v_payload(): Option[Empty] = {
    return ArtNative_Ext.observeOutPortValue(bridge.initialization_api.v_Id).asInstanceOf[Option[Empty]]
  }

}
