package com.nanda.archmvi2.data.repository

import android.content.Context
import com.nanda.archmvi2.data.api.ApiService
import com.nanda.archmvi2.data.model.ArticleResponse
import com.nanda.archmvi2.data.model.SourceResponse
import com.nanda.archmvi2.util.Constants

class NewsRepositoryImpl(private val apiService: ApiService, context: Context) :
    NewsRepository {

    override suspend fun getArticles(srouces: String?, page: Int): ArticleResponse {
        return apiService.everything(sources = srouces, page = page, pageSize = 10, apiKey = Constants.apiKey)
    }

    override suspend fun getSources(category: String?): SourceResponse {
        return apiService.sources(category = category, apiKey = Constants.apiKey)
    }

}