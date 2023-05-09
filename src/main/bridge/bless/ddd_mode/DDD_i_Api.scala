// #Sireum

package bless.ddd_mode

import org.sireum._
import art._
import bless._

@sig trait DDD_i_Api {
  def id: Art.BridgeId
  def a_Id : Art.PortId
  def v_Id : Art.PortId
  def ap_Id : Art.PortId
  def vp_Id : Art.PortId
  def as_Id : Art.PortId
  def vs_Id : Art.PortId

  def put_ap() : Unit = {
    Art.putValue(ap_Id, art.Empty())
  }

  def put_vp() : Unit = {
    Art.putValue(vp_Id, art.Empty())
  }

  def put_as() : Unit = {
    Art.putValue(as_Id, art.Empty())
  }

  def put_vs() : Unit = {
    Art.putValue(vs_Id, art.Empty())
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

@datatype class DDD_i_Initialization_Api (
  val id: Art.BridgeId,
  val a_Id : Art.PortId,
  val v_Id : Art.PortId,
  val ap_Id : Art.PortId,
  val vp_Id : Art.PortId,
  val as_Id : Art.PortId,
  val vs_Id : Art.PortId) extends DDD_i_Api

@datatype class DDD_i_Operational_Api (
  val id: Art.BridgeId,
  val a_Id : Art.PortId,
  val v_Id : Art.PortId,
  val ap_Id : Art.PortId,
  val vp_Id : Art.PortId,
  val as_Id : Art.PortId,
  val vs_Id : Art.PortId) extends DDD_i_Api {

  def get_a() : Option[art.Empty] = {
    val value : Option[art.Empty] = Art.getValue(a_Id) match {
      case Some(Empty()) => Some(Empty())
      case Some(v) =>
        Art.logError(id, s"Unexpected payload on port a.  Expecting 'Empty' but received ${v}")
        None[art.Empty]()
      case _ => None[art.Empty]()
    }
    return value
  }

  def get_v() : Option[art.Empty] = {
    val value : Option[art.Empty] = Art.getValue(v_Id) match {
      case Some(Empty()) => Some(Empty())
      case Some(v) =>
        Art.logError(id, s"Unexpected payload on port v.  Expecting 'Empty' but received ${v}")
        None[art.Empty]()
      case _ => None[art.Empty]()
    }
    return value
  }
}
