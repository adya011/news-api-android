package com.nanda.axiataassignment.data.api

import com.nanda.axiataassignment.data.model.ArticleResponse
import com.nanda.axiataassignment.data.model.SourceResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything")
    suspend fun everything(
        @Query("q") q: String? = null,
        @Query("from") from: String? = null,
        @Query("to") to: String? = null,
        @Query("sortBy") sortBy: String? = null,
        @Query("sources") sources: String? = null,
        @Query("apiKey") apiKey: String? = null
    ): ArticleResponse

    @GET("sources")
    suspend fun sources(
        @Query("category") category: String? = null,
        @Query("apiKey") apiKey: String? = null
    ): SourceResponse
}