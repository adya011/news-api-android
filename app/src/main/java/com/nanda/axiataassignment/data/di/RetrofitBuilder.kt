package com.nanda.axiataassignment.data.di

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "http://newsapi.org/v2/"

    fun <T> getRetrofit(api: Class<T>) =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(api)
}