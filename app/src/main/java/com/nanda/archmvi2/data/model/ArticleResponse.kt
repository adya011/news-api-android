package com.nanda.archmvi2.data.model

import com.squareup.moshi.Json

data class ArticleResponse(
    @Json(name = "status") val status: String = "",
    @Json(name = "totalResults") val totalResults: Int = 0,
    @Json(name = "articles") val articles: List<Article> = emptyList()
)