package com.armandorochin.borderwaittimes.data.remote.bwt.dtos

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "commercial_vehicle_lanes")
data class CommercialVehicleLanes(
    @field:PropertyElement(name = "maximum_lanes")
    var maximumLanes: String? = null,

    @field:Element(name = "standard_lanes")
    var standardLanes: StandardLanes? = null,

    @field:Element(name = "FAST_lanes")
    var fastLanes: FastLanes? = null
)