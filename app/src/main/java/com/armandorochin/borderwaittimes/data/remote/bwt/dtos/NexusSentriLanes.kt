package com.armandorochin.borderwaittimes.data.remote.bwt.dtos

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "NEXUS_SENTRI_lanes")
data class NexusSentriLanes(
    @field:PropertyElement(name = "update_time")
    var updateTime: String? = null,

    @field:PropertyElement(name = "operational_status")
    var operationalStatus: String? = null,

    @field:PropertyElement(name = "delay_minutes")
    var delayMinutes: String? = null,

    @field:PropertyElement(name = "lanes_open")
    var lanesOpen: String? = null
)
