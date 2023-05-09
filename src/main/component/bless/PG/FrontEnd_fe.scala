// #Sireum

package bless.PG

import org.sireum._
import bless._

// This file will not be overwritten so is safe to edit
object FrontEnd_fe {

  def initialise(api: FrontEnd_Initialization_Api): Unit = {
    FrontEndNative.initialise(api)
  }

  def handle_ap(api: FrontEnd_Operational_Api): Unit = {
    FrontEndNative.handle_ap(api)
    api.logInfo("received ap event")
  }

  def handle_vp(api: FrontEnd_Operational_Api): Unit = {
    FrontEndNative.handle_vp(api)
    api.logInfo("received vp event")
  }

  def activate(api: FrontEnd_Operational_Api): Unit = { }

  def deactivate(api: FrontEnd_Operational_Api): Unit = { }

  def finalise(api: FrontEnd_Operational_Api): Unit = { }

  def recover(api: FrontEnd_Operational_Api): Unit = { }
}

@ext object FrontEndNative {
  def initialise(api: FrontEnd_Initialization_Api): Unit = $
  def handle_ap(api: FrontEnd_Operational_Api): Unit = $
  def handle_vp(api: FrontEnd_Operational_Api): Unit = $
}
