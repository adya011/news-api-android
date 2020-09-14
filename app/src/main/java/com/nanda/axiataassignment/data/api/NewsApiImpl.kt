package com.nanda.axiataassignment.data.api

import android.content.Context
import com.nanda.axiataassignment.data.model.ArticleResponse
import com.nanda.axiataassignment.data.model.SourceResponse
import com.nanda.axiataassignment.util.Constants

class NewsApiImpl(private val apiService: ApiService, context: Context) : NewsApi {

    override suspend fun getArticles(): ArticleResponse {
        return apiService.everything(q = "apple", apiKey = Constants.apiKey)
    }

    override suspend fun getSources(): SourceResponse {
        return apiService.sources(apiKey = Constants.apiKey)
    }

}