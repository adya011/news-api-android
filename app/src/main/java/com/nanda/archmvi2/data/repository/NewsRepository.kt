package com.nanda.archmvi2.data.repository

import com.nanda.archmvi2.data.model.ArticleResponse
import com.nanda.archmvi2.data.model.SourceResponse

interface NewsRepository {
    suspend fun getArticles(sources: String? = null, page: Int = 1): ArticleResponse
    suspend fun getSources(category: String? = null): SourceResponse
}