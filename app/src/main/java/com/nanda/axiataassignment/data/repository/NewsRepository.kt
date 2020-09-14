package com.nanda.axiataassignment.data.repository

import com.nanda.axiataassignment.data.api.NewsApi

class NewsRepository(private val api: NewsApi) {
    suspend fun getArticles() = api.getArticles()
    suspend fun getSources() = api.getSources()
}