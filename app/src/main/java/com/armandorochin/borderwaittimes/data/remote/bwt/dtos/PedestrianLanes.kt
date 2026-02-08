package com.armandorochin.borderwaittimes.data.remote.bwt.dtos

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "pedestrian_lanes")
data class PedestrianLanes(
    @field:PropertyElement(name = "maximum_lanes")
    var maximumLanes: String? = null,

    @field:Element(name = "standard_lanes")
    var standardLanes: StandardLanes? = null,

    @field:Element(name = "ready_lanes")
    var readyLanes: ReadyLanes? = null
)
