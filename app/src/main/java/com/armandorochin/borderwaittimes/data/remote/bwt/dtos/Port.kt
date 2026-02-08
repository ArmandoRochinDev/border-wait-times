package com.armandorochin.borderwaittimes.data.remote.bwt.dtos

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "port")
data class Port(
    @field:PropertyElement(name = "port_number")
    var portNumber: String? = null,

    @field:PropertyElement(name = "border")
    var border: String? = null,

    @field:PropertyElement(name = "port_name")
    var portName: String? = null,

    @field:PropertyElement(name = "crossing_name")
    var crossingName: String? = null,

    @field:PropertyElement(name = "hours")
    var hours: String? = null,

    @field:PropertyElement(name = "date")
    var date: String? = null,

    @field:PropertyElement(name = "port_status")
    var portStatus: String? = null,

    @field:PropertyElement(name = "commercial_automation_type")
    var commercialAutomationType: String? = null,

    @field:PropertyElement(name = "passenger_automation_type")
    var passengerAutomationType: String? = null,

    @field:PropertyElement(name = "pedestrain_automation_type")
    var pedestrianAutomationType: String? = null,

    @field:Element(name = "commercial_vehicle_lanes")
    var commercialVehicleLanes: CommercialVehicleLanes? = null,

    @field:Element(name = "passenger_vehicle_lanes")
    var passengerVehicleLanes: PassengerVehicleLanes? = null,

    @field:Element(name = "pedestrian_lanes")
    var pedestrianLanes: PedestrianLanes? = null,

    @field:PropertyElement(name = "construction_notice")
    var constructionNotice: String? = null
)
