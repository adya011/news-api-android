package com.nanda.archmvi2.data.api

import com.nanda.archmvi2.data.model.ArticleResponse
import com.nanda.archmvi2.data.model.SourceResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything")
    suspend fun everything(
        @Query("q") q: String? = null,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
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