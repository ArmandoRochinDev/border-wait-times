package com.armandorochin.borderwaittimes.data.remote.bwt.dtos

import com.armandorochin.borderwaittimes.domain.model.BwtMetadata
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "border_wait_time")
data class BorderWaitTime(
    @field:PropertyElement(name = "last_updated_date")
    var lastUpdatedDate: String? = null,

    @field:PropertyElement(name = "last_updated_time")
    var lastUpdatedTime: String? = null,

    @field:PropertyElement(name = "number_of_ports")
    var numberOfPorts: Int? = null,

    @field:Element(name = "port")
    var ports: List<Port>? = null
){
    fun toBwtMetadata(): BwtMetadata{
        return BwtMetadata(
            lastUpdatedTime = lastUpdatedTime,
            lastUpdatedDate = lastUpdatedDate,
            numberOfPorts = numberOfPorts

        )
    }
}


