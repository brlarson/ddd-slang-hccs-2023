// #Sireum

package bless.PG

import org.sireum._
import art._
import bless._
import bless.PG.{FrontEnd_fe => component}

// This file was auto-generated.  Do not edit

@datatype class FrontEnd_fe_Bridge(
  val id: Art.BridgeId,
  val name: String,
  val dispatchProtocol: DispatchPropertyProtocol,
  val dispatchTriggers: Option[IS[Art.PortId, Art.PortId]],

  a: Port[art.Empty],
  v: Port[art.Empty],
  ap: Port[art.Empty],
  vp: Port[art.Empty]
  ) extends Bridge {

  val ports : Bridge.Ports = Bridge.Ports(
    all = IS[Art.PortId, art.UPort](a,
                                    v,
                                    ap,
                                    vp),

    dataIns = IS[Art.PortId, art.UPort](),

    dataOuts = IS[Art.PortId, art.UPort](),

    eventIns = IS[Art.PortId, art.UPort](ap,
                                         vp),

    eventOuts = IS[Art.PortId, art.UPort](a,
                                          v)
  )

  val initialization_api : FrontEnd_Initialization_Api = {
    val api = FrontEnd_Initialization_Api(
      id,
      a.id,
      v.id,
      ap.id,
      vp.id
    )
    FrontEnd_fe_Bridge.c_initialization_api = Some(api)
    api
  }

  val operational_api : FrontEnd_Operational_Api = {
    val api = FrontEnd_Operational_Api(
      id,
      a.id,
      v.id,
      ap.id,
      vp.id
    )
    FrontEnd_fe_Bridge.c_operational_api = Some(api)
    api
  }

  val entryPoints : Bridge.EntryPoints =
    FrontEnd_fe_Bridge.EntryPoints(
      id,

      a.id,
      v.id,
      ap.id,
      vp.id,

      dispatchTriggers,

      initialization_api,
      operational_api)
}

object FrontEnd_fe_Bridge {

  var c_initialization_api: Option[FrontEnd_Initialization_Api] = None()
  var c_operational_api: Option[FrontEnd_Operational_Api] = None()

  @datatype class EntryPoints(
    FrontEnd_fe_BridgeId : Art.BridgeId,
    a_Id : Art.PortId,
    v_Id : Art.PortId,
    ap_Id : Art.PortId,
    vp_Id : Art.PortId,
    dispatchTriggers : Option[IS[Art.PortId, Art.PortId]],
    initialization_api: FrontEnd_Initialization_Api,
    operational_api: FrontEnd_Operational_Api) extends Bridge.EntryPoints {

    val dataInPortIds: IS[Art.PortId, Art.PortId] = IS()

    val eventInPortIds: IS[Art.PortId, Art.PortId] = IS(ap_Id,
                                                        vp_Id)

    val dataOutPortIds: IS[Art.PortId, Art.PortId] = IS()

    val eventOutPortIds: IS[Art.PortId, Art.PortId] = IS(a_Id,
                                                         v_Id)

    def initialise(): Unit = {
      // implement the following method in 'component':  def initialise(api: FrontEnd_Initialization_Api): Unit = {}
      component.initialise(initialization_api)
      Art.sendOutput(eventOutPortIds, dataOutPortIds)
    }

    def compute(): Unit = {
      // transpiler friendly filter
      def filter(receivedEvents: IS[Art.PortId, Art.PortId], triggers: IS[Art.PortId, Art.PortId]): IS[Art.PortId, Art.PortId] = {
        var r = IS[Art.PortId, Art.PortId]()
        val opsTriggers = art.ops.ISPOps(triggers)
        for(e <- receivedEvents) {
          if(opsTriggers.contains(e)) {
            r = r :+ e
          }
        }
        return r
      }

      // fetch received events ordered by highest urgency then earliest arrival-time
      val EventTriggered(receivedEvents) = Art.dispatchStatus(FrontEnd_fe_BridgeId)

      // remove non-dispatching event ports
      val dispatchableEventPorts: IS[Art.PortId, Art.PortId] =
        if(dispatchTriggers.isEmpty) receivedEvents
        else filter(receivedEvents, dispatchTriggers.get)

      Art.receiveInput(eventInPortIds, dataInPortIds)

      for(portId <- dispatchableEventPorts) {
        if(portId == ap_Id) {
          // implement the following in 'component':  def handle_ap(api: FrontEnd_Operational_Api): Unit = {}
          component.handle_ap(operational_api)
        }
        else if(portId == vp_Id) {
          // implement the following in 'component':  def handle_vp(api: FrontEnd_Operational_Api): Unit = {}
          component.handle_vp(operational_api)
        }
      }

      Art.sendOutput(eventOutPortIds, dataOutPortIds)
    }

    def activate(): Unit = {
      // implement the following method in 'component':  def activate(api: FrontEnd_Operational_Api): Unit = {}
      component.activate(operational_api)
    }

    def deactivate(): Unit = {
      // implement the following method in 'component':  def deactivate(api: FrontEnd_Operational_Api): Unit = {}
      component.deactivate(operational_api)
    }

    def finalise(): Unit = {
      // implement the following method in 'component':  def finalise(api: FrontEnd_Operational_Api): Unit = {}
      component.finalise(operational_api)
    }

    def recover(): Unit = {
      // implement the following method in 'component':  def recover(api: FrontEnd_Operational_Api): Unit = {}
      component.recover(operational_api)
    }

    override
    def testInitialise(): Unit = {
      // implement the following method in 'component':  def initialise(api: FrontEnd_Initialization_Api): Unit = {}
      component.initialise(initialization_api)
      Art.releaseOutput(eventOutPortIds, dataOutPortIds)
    }

    override
    def testCompute(): Unit = {
      // transpiler friendly filter
      def filter(receivedEvents: IS[Art.PortId, Art.PortId], triggers: IS[Art.PortId, Art.PortId]): IS[Art.PortId, Art.PortId] = {
        var r = IS[Art.PortId, Art.PortId]()
        val opsTriggers = art.ops.ISPOps(triggers)
        for(e <- receivedEvents) {
          if(opsTriggers.contains(e)) {
            r = r :+ e
          }
        }
        return r
      }

      // fetch received events ordered by highest urgency then earliest arrival-time
      val EventTriggered(receivedEvents) = Art.dispatchStatus(FrontEnd_fe_BridgeId)

      // remove non-dispatching event ports
      val dispatchableEventPorts: IS[Art.PortId, Art.PortId] =
        if(dispatchTriggers.isEmpty) receivedEvents
        else filter(receivedEvents, dispatchTriggers.get)

      Art.receiveInput(eventInPortIds, dataInPortIds)

      for(portId <- dispatchableEventPorts) {
        if(portId == ap_Id) {
          // implement the following in 'component':  def handle_ap(api: FrontEnd_Operational_Api): Unit = {}
          component.handle_ap(operational_api)
        }
        else if(portId == vp_Id) {
          // implement the following in 'component':  def handle_vp(api: FrontEnd_Operational_Api): Unit = {}
          component.handle_vp(operational_api)
        }
      }

      Art.releaseOutput(eventOutPortIds, dataOutPortIds)
    }
  }
}