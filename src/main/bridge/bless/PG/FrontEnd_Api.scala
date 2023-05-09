// #Sireum

package bless.PG

import org.sireum._
import art._
import bless._

@sig trait FrontEnd_Api {
  def id: Art.BridgeId
  def a_Id : Art.PortId
  def v_Id : Art.PortId
  def ap_Id : Art.PortId
  def vp_Id : Art.PortId

  def put_a() : Unit = {
    Art.putValue(a_Id, art.Empty())
  }

  def put_v() : Unit = {
    Art.putValue(v_Id, art.Empty())
  }

  def logInfo(msg: String): Unit = {
    Art.logInfo(id, msg)
  }

  def logDebug(msg: String): Unit = {
    Art.logDebug(id, msg)
  }

  def logError(msg: String): Unit = {
    Art.logError(id, msg)
  }
}

@datatype class FrontEnd_Initialization_Api (
  val id: Art.BridgeId,
  val a_Id : Art.PortId,
  val v_Id : Art.PortId,
  val ap_Id : Art.PortId,
  val vp_Id : Art.PortId) extends FrontEnd_Api

@datatype class FrontEnd_Operational_Api (
  val id: Art.BridgeId,
  val a_Id : Art.PortId,
  val v_Id : Art.PortId,
  val ap_Id : Art.PortId,
  val vp_Id : Art.PortId) extends FrontEnd_Api {

  def get_ap() : Option[art.Empty] = {
    val value : Option[art.Empty] = Art.getValue(ap_Id) match {
      case Some(Empty()) => Some(Empty())
      case Some(v) =>
        Art.logError(id, s"Unexpected payload on port ap.  Expecting 'Empty' but received ${v}")
        None[art.Empty]()
      case _ => None[art.Empty]()
    }
    return value
  }

  def get_vp() : Option[art.Empty] = {
    val value : Option[art.Empty] = Art.getValue(vp_Id) match {
      case Some(Empty()) => Some(Empty())
      case Some(v) =>
        Art.logError(id, s"Unexpected payload on port vp.  Expecting 'Empty' but received ${v}")
        None[art.Empty]()
      case _ => None[art.Empty]()
    }
    return value
  }
}
