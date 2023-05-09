// #Sireum

package bless.ddd_mode

import org.sireum._
import art._
import bless._
import bless.ddd_mode.{DDD_i_ddd_ddd => component}
import org.sireum.S64._

// This file was auto-generated.  Do not edit

@datatype class DDD_i_ddd_ddd_Bridge(
  val id: Art.BridgeId,
  val name: String,
  val dispatchProtocol: DispatchPropertyProtocol,
  val dispatchTriggers: Option[IS[Art.PortId, Art.PortId]],

  a: Port[art.Empty],
  v: Port[art.Empty],
  ap: Port[art.Empty],
  vp: Port[art.Empty],
  as: Port[art.Empty],
  vs: Port[art.Empty]
  ) extends Bridge {

  val ports : Bridge.Ports = Bridge.Ports(
    all = IS[Art.PortId, art.UPort](a,
                                    v,
                                    ap,
                                    vp,
                                    as,
                                    vs),

    dataIns = IS[Art.PortId, art.UPort](),

    dataOuts = IS[Art.PortId, art.UPort](),

    eventIns = IS[Art.PortId, art.UPort](a,
                                         v),

    eventOuts = IS[Art.PortId, art.UPort](ap,
                                          vp,
                                          as,
                                          vs)
  )

  val initialization_api : DDD_i_Initialization_Api = {
    val api = DDD_i_Initialization_Api(
      id,
      a.id,
      v.id,
      ap.id,
      vp.id,
      as.id,
      vs.id
    )
    DDD_i_ddd_ddd_Bridge.c_initialization_api = Some(api)
    api
  }

  val operational_api : DDD_i_Operational_Api = {
    val api = DDD_i_Operational_Api(
      id,
      a.id,
      v.id,
      ap.id,
      vp.id,
      as.id,
      vs.id
    )
    DDD_i_ddd_ddd_Bridge.c_operational_api = Some(api)
    api
  }

  val entryPoints : Bridge.EntryPoints =
    DDD_i_ddd_ddd_Bridge.EntryPoints(
      id,

      a.id,
      v.id,
      ap.id,
      vp.id,
      as.id,
      vs.id,

      dispatchTriggers,

      initialization_api,
      operational_api)
}

object DDD_i_ddd_ddd_Bridge {

  var c_initialization_api: Option[DDD_i_Initialization_Api] = None()
  var c_operational_api: Option[DDD_i_Operational_Api] = None()
  // I'm a companion object block

  def contains(isz : ISZ[Art.PortId], elt : Art.PortId) : B =
    { for ( i <- isz )
      { if (i == elt) { return T } }
    return F
    }

  var event_set : Set[Art.PortId] = Set.empty[Art.PortId]

  var timeout_id : Art.PortId = Art.PortId.fromZ(Art.maxPorts+1)

  //timeout variables
  val timeout_as_Sensed_AV_Delay_ms_ID : Art.PortId = timeout_id+Art.PortId.fromZ(1)
  var timeout_as_Sensed_AV_Delay_ms_Duration : Art.Time = s64"0"
  val timeout_vp_vs_Lower_Rate_Limit_Interval_ms_ID : Art.PortId = timeout_id+Art.PortId.fromZ(2)
  var timeout_vp_vs_Lower_Rate_Limit_Interval_ms_Duration : Art.Time = s64"0"
  val timeout_vp_vs_va_interval_ID : Art.PortId = timeout_id+Art.PortId.fromZ(3)
  var timeout_vp_vs_va_interval_Duration : Art.Time = s64"0"


  //timeout callback classes
  @datatype class Callback_timeout_as_Sensed_AV_Delay_ms(ep : EntryPoints) extends TimerCallback
    {
    override def callback(): Unit = { ep.timeout_as_Sensed_AV_Delay_ms_expires( mc = this ) }
    }
  @datatype class Callback_timeout_vp_vs_Lower_Rate_Limit_Interval_ms(ep : EntryPoints) extends TimerCallback
    {
    override def callback(): Unit = { ep.timeout_vp_vs_Lower_Rate_Limit_Interval_ms_expires( mc = this ) }
    }
  @datatype class Callback_timeout_vp_vs_va_interval(ep : EntryPoints) extends TimerCallback
    {
    override def callback(): Unit = { ep.timeout_vp_vs_va_interval_expires( mc = this ) }
    }


  @datatype class EntryPoints(
    DDD_i_ddd_ddd_BridgeId : Art.BridgeId,
    a_Id : Art.PortId,
    v_Id : Art.PortId,
    ap_Id : Art.PortId,
    vp_Id : Art.PortId,
    as_Id : Art.PortId,
    vs_Id : Art.PortId,
    dispatchTriggers : Option[IS[Art.PortId, Art.PortId]],
    initialization_api: DDD_i_Initialization_Api,
    operational_api: DDD_i_Operational_Api) extends Bridge.EntryPoints {

    val dataInPortIds: IS[Art.PortId, Art.PortId] = IS()

    val eventInPortIds: IS[Art.PortId, Art.PortId] = IS(a_Id,
                                                        v_Id)

    val dataOutPortIds: IS[Art.PortId, Art.PortId] = IS()

    val eventOutPortIds: IS[Art.PortId, Art.PortId] = IS(ap_Id,
                                                         vp_Id,
                                                         as_Id,
                                                         vs_Id)

    //timeout callback methods
    def timeout_as_Sensed_AV_Delay_ms_expires(mc: Callback_timeout_as_Sensed_AV_Delay_ms): Unit =
      {
      operational_api.logDebug("timeout_as_Sensed_AV_Delay_ms expires")
      event_set = event_set + timeout_as_Sensed_AV_Delay_ms_ID
      compute()
      }
    def timeout_vp_vs_Lower_Rate_Limit_Interval_ms_expires(mc: Callback_timeout_vp_vs_Lower_Rate_Limit_Interval_ms): Unit =
      {
      operational_api.logDebug("timeout_vp_vs_Lower_Rate_Limit_Interval_ms expires")
      event_set = event_set + timeout_vp_vs_Lower_Rate_Limit_Interval_ms_ID
      compute()
      }
    def timeout_vp_vs_va_interval_expires(mc: Callback_timeout_vp_vs_va_interval): Unit =
      {
      operational_api.logDebug("timeout_vp_vs_va_interval expires")
      event_set = event_set + timeout_vp_vs_va_interval_ID
      compute()
      }

    def initialise(): Unit = {
      // i'm an initialize body
        initialization_api.logDebug("DDD_i_ddd_ddd_Bridge.initialise()")  
        component.Initialize_Entrypoint(initialization_api)
        if ( Art.observeOutPortValue(as_Id).nonEmpty )
          { ArtTimer.scheduleTrait("timeout_as_Sensed_AV_Delay_ms", T, timeout_as_Sensed_AV_Delay_ms_Duration, Callback_timeout_as_Sensed_AV_Delay_ms(this)) }
          if ( Art.observeOutPortValue(vp_Id).nonEmpty | Art.observeOutPortValue(vs_Id).nonEmpty )
          { ArtTimer.scheduleTrait("timeout_vp_vs_Lower_Rate_Limit_Interval_ms", T, timeout_vp_vs_Lower_Rate_Limit_Interval_ms_Duration, Callback_timeout_vp_vs_Lower_Rate_Limit_Interval_ms(this)) }
          if ( Art.observeOutPortValue(vp_Id).nonEmpty | Art.observeOutPortValue(vs_Id).nonEmpty )
          { ArtTimer.scheduleTrait("timeout_vp_vs_va_interval", T, timeout_vp_vs_va_interval_Duration, Callback_timeout_vp_vs_va_interval(this)) }
        Art.sendOutput(eventOutPortIds, dataOutPortIds)
    }

    def compute(): Unit = {
      // i'm a compute body
        operational_api.logDebug("DDD_i_ddd_ddd_Bridge.compute()")  
        val EventTriggered(receivedEvents) = Art.dispatchStatus(DDD_i_ddd_ddd_BridgeId)
        event_set = event_set ++ receivedEvents  
        Art.receiveInput(eventInPortIds, dataInPortIds)
        val dispatched : B = { component.Compute_Entrypoint(operational_api, event_set) }
        if (dispatched) { event_set = Set.empty[Art.PortId] }
        if ( Art.observeOutPortValue(as_Id).nonEmpty )
          { ArtTimer.scheduleTrait("timeout_as_Sensed_AV_Delay_ms", T, timeout_as_Sensed_AV_Delay_ms_Duration, Callback_timeout_as_Sensed_AV_Delay_ms(this)) }
          if ( Art.observeOutPortValue(vp_Id).nonEmpty | Art.observeOutPortValue(vs_Id).nonEmpty )
          { ArtTimer.scheduleTrait("timeout_vp_vs_Lower_Rate_Limit_Interval_ms", T, timeout_vp_vs_Lower_Rate_Limit_Interval_ms_Duration, Callback_timeout_vp_vs_Lower_Rate_Limit_Interval_ms(this)) }
          if ( Art.observeOutPortValue(vp_Id).nonEmpty | Art.observeOutPortValue(vs_Id).nonEmpty )
          { ArtTimer.scheduleTrait("timeout_vp_vs_va_interval", T, timeout_vp_vs_va_interval_Duration, Callback_timeout_vp_vs_va_interval(this)) }
        Art.sendOutput(eventOutPortIds, dataOutPortIds)
    }

    def activate(): Unit = {
      // i'm an activate body
    }

    def deactivate(): Unit = {
      // i'm a deactivate body
    }

    def finalise(): Unit = {
      // i'm a finalize body
      Art.receiveInput(eventInPortIds, dataInPortIds)
      component.Finalize_Entrypoint(operational_api)
    }

    def recover(): Unit = {
      // i'm a recover body
    }

    override
    def testInitialise(): Unit = {
      // i'm an initialize body
        initialization_api.logDebug("DDD_i_ddd_ddd_Bridge.initialise()")  
        component.Initialize_Entrypoint(initialization_api)
        if ( Art.observeOutPortValue(as_Id).nonEmpty )
          { ArtTimer.scheduleTrait("timeout_as_Sensed_AV_Delay_ms", T, timeout_as_Sensed_AV_Delay_ms_Duration, Callback_timeout_as_Sensed_AV_Delay_ms(this)) }
          if ( Art.observeOutPortValue(vp_Id).nonEmpty | Art.observeOutPortValue(vs_Id).nonEmpty )
          { ArtTimer.scheduleTrait("timeout_vp_vs_Lower_Rate_Limit_Interval_ms", T, timeout_vp_vs_Lower_Rate_Limit_Interval_ms_Duration, Callback_timeout_vp_vs_Lower_Rate_Limit_Interval_ms(this)) }
          if ( Art.observeOutPortValue(vp_Id).nonEmpty | Art.observeOutPortValue(vs_Id).nonEmpty )
          { ArtTimer.scheduleTrait("timeout_vp_vs_va_interval", T, timeout_vp_vs_va_interval_Duration, Callback_timeout_vp_vs_va_interval(this)) }
        Art.sendOutput(eventOutPortIds, dataOutPortIds)
    }

    override
    def testCompute(): Unit = {
      // i'm a compute body
        operational_api.logDebug("DDD_i_ddd_ddd_Bridge.compute()")  
        val EventTriggered(receivedEvents) = Art.dispatchStatus(DDD_i_ddd_ddd_BridgeId)
        event_set = event_set ++ receivedEvents  
        Art.receiveInput(eventInPortIds, dataInPortIds)
        val dispatched : B = { component.Compute_Entrypoint(operational_api, event_set) }
        if (dispatched) { event_set = Set.empty[Art.PortId] }
        if ( Art.observeOutPortValue(as_Id).nonEmpty )
          { ArtTimer.scheduleTrait("timeout_as_Sensed_AV_Delay_ms", T, timeout_as_Sensed_AV_Delay_ms_Duration, Callback_timeout_as_Sensed_AV_Delay_ms(this)) }
          if ( Art.observeOutPortValue(vp_Id).nonEmpty | Art.observeOutPortValue(vs_Id).nonEmpty )
          { ArtTimer.scheduleTrait("timeout_vp_vs_Lower_Rate_Limit_Interval_ms", T, timeout_vp_vs_Lower_Rate_Limit_Interval_ms_Duration, Callback_timeout_vp_vs_Lower_Rate_Limit_Interval_ms(this)) }
          if ( Art.observeOutPortValue(vp_Id).nonEmpty | Art.observeOutPortValue(vs_Id).nonEmpty )
          { ArtTimer.scheduleTrait("timeout_vp_vs_va_interval", T, timeout_vp_vs_va_interval_Duration, Callback_timeout_vp_vs_va_interval(this)) }
        Art.sendOutput(eventOutPortIds, dataOutPortIds)
    }
  }
}