package com.nanda.archmvi2.data.di

import com.nanda.archmvi2.data.repository.NewsRepository
import com.nanda.archmvi2.data.repository.NewsRepositoryImpl
import com.nanda.archmvi2.data.api.ApiService
import com.nanda.archmvi2.ui.viewmodel.ArticleViewModel
import com.nanda.archmvi2.ui.viewmodel.SourceViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

object Modules {
    private val networkModules = module {
        single {
            RetrofitBuilder.getRetrofit(
                ApiService::class.java
            )
        }
    }

    private val viewModelModules = module {
        viewModel { ArticleViewModel(get()) }
        viewModel { SourceViewModel(get()) }
    }

    private val apiModules = module {
        single<NewsRepository> {
            NewsRepositoryImpl(
                get(),
                get()
            )
        }
    }

    fun getAll() = listOf(
        networkModules,
        viewModelModules,
        apiModules
    )
}