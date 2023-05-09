// #Sireum

package bless

import org.sireum._
import art._
import art.PortMode._
import art.DispatchPropertyProtocol._
import art.Art.BridgeId._
import art.Art.PortId._

// This file was auto-generated.  Do not edit

object Arch {
  val Pacemaker_imp_Instance_fe : bless.PG.FrontEnd_fe_Bridge = {
    val a = Port[art.Empty] (id = portId"0", name = "Pacemaker_imp_Instance_fe_a", mode = EventOut)
    val v = Port[art.Empty] (id = portId"1", name = "Pacemaker_imp_Instance_fe_v", mode = EventOut)
    val ap = Port[art.Empty] (id = portId"2", name = "Pacemaker_imp_Instance_fe_ap", mode = EventIn)
    val vp = Port[art.Empty] (id = portId"3", name = "Pacemaker_imp_Instance_fe_vp", mode = EventIn)

    bless.PG.FrontEnd_fe_Bridge(
      id = bridgeId"0",
      name = "Pacemaker_imp_Instance_fe",
      dispatchProtocol = Sporadic(min = 1),
      dispatchTriggers = None(),

      a = a,
      v = v,
      ap = ap,
      vp = vp
    )
  }
  val Pacemaker_imp_Instance_ddd_ddd : bless.ddd_mode.DDD_i_ddd_ddd_Bridge = {
    val a = Port[art.Empty] (id = portId"4", name = "Pacemaker_imp_Instance_ddd_ddd_a", mode = EventIn)
    val v = Port[art.Empty] (id = portId"5", name = "Pacemaker_imp_Instance_ddd_ddd_v", mode = EventIn)
    val ap = Port[art.Empty] (id = portId"6", name = "Pacemaker_imp_Instance_ddd_ddd_ap", mode = EventOut)
    val vp = Port[art.Empty] (id = portId"7", name = "Pacemaker_imp_Instance_ddd_ddd_vp", mode = EventOut)
    val as = Port[art.Empty] (id = portId"8", name = "Pacemaker_imp_Instance_ddd_ddd_as", mode = EventOut)
    val vs = Port[art.Empty] (id = portId"9", name = "Pacemaker_imp_Instance_ddd_ddd_vs", mode = EventOut)

    bless.ddd_mode.DDD_i_ddd_ddd_Bridge(
      id = bridgeId"1",
      name = "Pacemaker_imp_Instance_ddd_ddd",
      dispatchProtocol = Sporadic(min = 1),
      dispatchTriggers = None(),

      a = a,
      v = v,
      ap = ap,
      vp = vp,
      as = as,
      vs = vs
    )
  }

  val ad : ArchitectureDescription = {

    ArchitectureDescription(
      components = IS[Art.BridgeId, Bridge] (Pacemaker_imp_Instance_fe, Pacemaker_imp_Instance_ddd_ddd),

      connections = IS[Art.ConnectionId, UConnection] (Connection(from = Pacemaker_imp_Instance_fe.a, to = Pacemaker_imp_Instance_ddd_ddd.a),
                                                       Connection(from = Pacemaker_imp_Instance_fe.v, to = Pacemaker_imp_Instance_ddd_ddd.v),
                                                       Connection(from = Pacemaker_imp_Instance_ddd_ddd.ap, to = Pacemaker_imp_Instance_fe.ap),
                                                       Connection(from = Pacemaker_imp_Instance_ddd_ddd.vp, to = Pacemaker_imp_Instance_fe.vp))
    )
  }
}
