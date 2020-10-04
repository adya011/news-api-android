package com.nanda.archmvi2.data.model

import com.squareup.moshi.Json

data class SourceResponse(
    @Json(name = "status") val status: String? = "",
    @Json(name = "sources") val sources: List<Source> = emptyList()

)