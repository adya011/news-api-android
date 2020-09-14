package com.nanda.axiataassignment.data.di

import com.nanda.axiataassignment.data.api.NewsApi
import com.nanda.axiataassignment.data.api.NewsApiImpl
import com.nanda.axiataassignment.data.api.ApiService
import com.nanda.axiataassignment.data.repository.NewsRepository
import com.nanda.axiataassignment.ui.viewmodel.ArticleViewModel
import com.nanda.axiataassignment.ui.viewmodel.SourceViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

object Modules {
    private val networkModules = module {
        single {
            RetrofitBuilder.create(
                ApiService::class.java
            )
        }
    }

    private val viewModelModules = module {
        viewModel { ArticleViewModel(get()) }
        viewModel { SourceViewModel(get()) }
    }

    private val apiModules = module {
        single<NewsApi> {
            NewsApiImpl(get(), get())
        }
    }

    private val repoModules = module {
        single { NewsRepository(get()) }
    }

    fun getAll() = listOf(
        networkModules,
        viewModelModules,
        apiModules,
        repoModules
    )
}