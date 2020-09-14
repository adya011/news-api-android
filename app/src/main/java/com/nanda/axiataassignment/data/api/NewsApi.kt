package com.nanda.axiataassignment.data.api

import com.nanda.axiataassignment.data.model.ArticleResponse
import com.nanda.axiataassignment.data.model.SourceResponse

interface NewsApi {
    suspend fun getArticles(): ArticleResponse
    suspend fun getSources(): SourceResponse
}