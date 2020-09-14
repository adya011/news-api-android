package com.nanda.axiataassignment.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nanda.axiataassignment.data.repository.NewsRepository
import com.nanda.axiataassignment.ui.intent.SourceIntent
import com.nanda.axiataassignment.ui.viewstate.SourceState
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.collect
import kotlin.coroutines.CoroutineContext

class SourceViewModel(
    private val newsRepo: NewsRepository
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.Main

    val userIntent = Channel<SourceIntent>(Channel.UNLIMITED)

    private val _state = MutableStateFlow<SourceState>(SourceState.Idle)
    val state: StateFlow<SourceState>
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
                    is SourceIntent.FetchSources -> fetchSources()
                }
            }
        }
    }

    private fun fetchSources() {
        viewModelScope.launch {
            _state.value = SourceState.Loading

            _state.value = try {
                SourceState.News(newsRepo.getSources())
            } catch (e: Exception) {
                SourceState.Error(e.localizedMessage)
            }
        }
    }
}