package com.nanda.axiataassignment.data.repository

import com.nanda.axiataassignment.data.model.ArticleResponse
import com.nanda.axiataassignment.data.model.SourceResponse

interface NewsRepository {
    suspend fun getArticles(sources: String? = null): ArticleResponse
    suspend fun getSources(category: String? = null): SourceResponse
}