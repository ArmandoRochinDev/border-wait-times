package com.armandorochin.borderwaittimes.domain.model

import com.tickaroo.tikxml.annotation.PropertyElement

data class BwtMetadata(
    var lastUpdatedDate: String? = null,
    var lastUpdatedTime: String? = null,
    var numberOfPorts: Int? = null,
)
