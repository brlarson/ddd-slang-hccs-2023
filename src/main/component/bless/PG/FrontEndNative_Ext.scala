package bless.PG

import bless.UNO.UNOBridge

object FrontEndNative_Ext {

  def initialise(api: FrontEnd_Initialization_Api): Unit = {
    api.logInfo("Starting Arduino UNO initialization")
    UNOBridge.init(api)
  } //end of initialise

  def handle_ap(api: FrontEnd_Operational_Api): Unit = {
    UNOBridge.putAP(api)
  }

  def handle_vp(api: FrontEnd_Operational_Api): Unit = {
    UNOBridge.putVP(api)
  }

  //  def timeTriggered(api: FrontEnd_Operational_Api): Unit = {
//    val apiUsage_pace: org.sireum.Option[Empty] = api.get_pace()
//    if (apiUsage_pace.nonEmpty) {
//      UNOBridge.putVP(api)
//    }
//  }

}
