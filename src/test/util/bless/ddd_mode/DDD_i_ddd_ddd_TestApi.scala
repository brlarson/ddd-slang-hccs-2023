package bless.ddd_mode

import org.sireum._
import art.{ArtNative_Ext, Empty}
import bless._

// This file was auto-generated.  Do not edit
abstract class DDD_i_ddd_ddd_TestApi extends BridgeTestSuite[DDD_i_ddd_ddd_Bridge](Arch.Pacemaker_imp_Instance_ddd_ddd) {

  /** helper function to set the values of all input ports.
   * @param a the number of events to place in the a event port queue.
   *   ART currently supports single element event queues so at most
   *   one event will be placed in the queue.
   * @param v the number of events to place in the v event port queue.
   *   ART currently supports single element event queues so at most
   *   one event will be placed in the queue.
   */
  def put_concrete_inputs(a : Z,
                          v : Z): Unit = {
    for(i <- 0 until a) {
      put_a()
    }
    for(i <- 0 until v) {
      put_v()
    }
  }


  /** helper function to check DDD_i_ddd_ddd's
   * output ports.  Use named arguments to check subsets of the output ports.
   * @param ap method that will be called with the number of events to be sent
   *        on the outgoing event port 'ap'.
   * @param vp method that will be called with the number of events to be sent
   *        on the outgoing event port 'vp'.
   * @param as method that will be called with the number of events to be sent
   *        on the outgoing event port 'as'.
   * @param vs method that will be called with the number of events to be sent
   *        on the outgoing event port 'vs'.
   */
  def check_concrete_output(ap: Z => B = apParam => {T},
                            vp: Z => B = vpParam => {T},
                            as: Z => B = asParam => {T},
                            vs: Z => B = vsParam => {T}): Unit = {
    var testFailures: ISZ[ST] = ISZ()

    // TODO: event port getter should return the number of events in
    //       the output queue when queue sizes > 1 support is added to ART
    val apValue: Z = if(get_ap().nonEmpty) z"1" else z"0"
    if(!ap(apValue)) {
      testFailures = testFailures :+ st"'ap' did not match expected: ${apValue} events were in the outgoing event queue"
    }
    // TODO: event port getter should return the number of events in
    //       the output queue when queue sizes > 1 support is added to ART
    val vpValue: Z = if(get_vp().nonEmpty) z"1" else z"0"
    if(!vp(vpValue)) {
      testFailures = testFailures :+ st"'vp' did not match expected: ${vpValue} events were in the outgoing event queue"
    }
    // TODO: event port getter should return the number of events in
    //       the output queue when queue sizes > 1 support is added to ART
    val asValue: Z = if(get_as().nonEmpty) z"1" else z"0"
    if(!as(asValue)) {
      testFailures = testFailures :+ st"'as' did not match expected: ${asValue} events were in the outgoing event queue"
    }
    // TODO: event port getter should return the number of events in
    //       the output queue when queue sizes > 1 support is added to ART
    val vsValue: Z = if(get_vs().nonEmpty) z"1" else z"0"
    if(!vs(vsValue)) {
      testFailures = testFailures :+ st"'vs' did not match expected: ${vsValue} events were in the outgoing event queue"
    }

    assert(testFailures.isEmpty, st"${(testFailures, "\n")}".render)
  }


  // setter for in EventPort
  def put_a(): Unit = {
    ArtNative_Ext.insertInPortValue(bridge.operational_api.a_Id, Empty())
  }

  // setter for in EventPort
  def put_v(): Unit = {
    ArtNative_Ext.insertInPortValue(bridge.operational_api.v_Id, Empty())
  }

  // getter for out EventPort
  def get_ap(): Option[art.Empty] = {
    val value: Option[art.Empty] = get_ap_payload() match {
      case Some(Empty()) => Some(Empty())
      case Some(v) => fail(s"Unexpected payload on port ap.  Expecting 'Empty' but received ${v}")
      case _ => None[art.Empty]()
    }
    return value
  }

  // payload getter for out EventPort
  def get_ap_payload(): Option[Empty] = {
    return ArtNative_Ext.observeOutPortValue(bridge.initialization_api.ap_Id).asInstanceOf[Option[Empty]]
  }

  // getter for out EventPort
  def get_vp(): Option[art.Empty] = {
    val value: Option[art.Empty] = get_vp_payload() match {
      case Some(Empty()) => Some(Empty())
      case Some(v) => fail(s"Unexpected payload on port vp.  Expecting 'Empty' but received ${v}")
      case _ => None[art.Empty]()
    }
    return value
  }

  // payload getter for out EventPort
  def get_vp_payload(): Option[Empty] = {
    return ArtNative_Ext.observeOutPortValue(bridge.initialization_api.vp_Id).asInstanceOf[Option[Empty]]
  }

  // getter for out EventPort
  def get_as(): Option[art.Empty] = {
    val value: Option[art.Empty] = get_as_payload() match {
      case Some(Empty()) => Some(Empty())
      case Some(v) => fail(s"Unexpected payload on port as.  Expecting 'Empty' but received ${v}")
      case _ => None[art.Empty]()
    }
    return value
  }

  // payload getter for out EventPort
  def get_as_payload(): Option[Empty] = {
    return ArtNative_Ext.observeOutPortValue(bridge.initialization_api.as_Id).asInstanceOf[Option[Empty]]
  }

  // getter for out EventPort
  def get_vs(): Option[art.Empty] = {
    val value: Option[art.Empty] = get_vs_payload() match {
      case Some(Empty()) => Some(Empty())
      case Some(v) => fail(s"Unexpected payload on port vs.  Expecting 'Empty' but received ${v}")
      case _ => None[art.Empty]()
    }
    return value
  }

  // payload getter for out EventPort
  def get_vs_payload(): Option[Empty] = {
    return ArtNative_Ext.observeOutPortValue(bridge.initialization_api.vs_Id).asInstanceOf[Option[Empty]]
  }

}
