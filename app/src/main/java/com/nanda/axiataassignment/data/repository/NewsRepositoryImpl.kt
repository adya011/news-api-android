package com.nanda.axiataassignment.data.repository

import android.content.Context
import com.nanda.axiataassignment.data.api.ApiService
import com.nanda.axiataassignment.data.model.ArticleResponse
import com.nanda.axiataassignment.data.model.SourceResponse
import com.nanda.axiataassignment.util.Constants

class NewsRepositoryImpl(private val apiService: ApiService, context: Context) :
    NewsRepository {

    override suspend fun getArticles(srouces: String?): ArticleResponse {
        return apiService.everything(sources = srouces, apiKey = Constants.apiKey)
    }

    override suspend fun getSources(category: String?): SourceResponse {
        return apiService.sources(category = category, apiKey = Constants.apiKey)
    }

}