package com.nanda.axiataassignment.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nanda.axiataassignment.data.repository.NewsRepository
import com.nanda.axiataassignment.ui.intent.ArticleIntent
import com.nanda.axiataassignment.ui.viewstate.ArticleState
import com.nanda.axiataassignment.ui.viewstate.ArticleViewState
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.collect
import kotlin.coroutines.CoroutineContext


class ArticleViewModel(
    private val repository: NewsRepository
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.Main

    val userIntent = Channel<ArticleIntent>(Channel.UNLIMITED)
    var viewState: ArticleViewState = ArticleViewState()

    private val _state = MutableStateFlow<ArticleState>(ArticleState.Idle)
    val state: StateFlow<ArticleState>
        get() = _state

    init {
        handleIntent()
    }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancel()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is ArticleIntent.FetchArticle -> fetchNews()
                }
            }
        }
    }

    private fun fetchNews() {
        val sourceId = viewState.sourceId

        viewModelScope.launch {
            _state.value = ArticleState.Loading

            _state.value =
                try {
                    ArticleState.News(repository.getArticles(sourceId))
                } catch (e: Exception) {
                    ArticleState.Error(e.localizedMessage)
                }
        }
    }
}