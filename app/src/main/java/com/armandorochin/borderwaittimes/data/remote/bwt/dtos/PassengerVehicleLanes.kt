package com.armandorochin.borderwaittimes.data.remote.bwt.dtos

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "passenger_vehicle_lanes")
data class PassengerVehicleLanes(
    @field:PropertyElement(name = "maximum_lanes")
    var maximumLanes: String? = null,

    @field:Element(name = "standard_lanes")
    var standardLanes: StandardLanes? = null,

    @field:Element(name = "NEXUS_SENTRI_lanes")
    var nexusSentriLanes: NexusSentriLanes? = null,

    @field:Element(name = "ready_lanes")
    var readyLanes: ReadyLanes? = null
)
