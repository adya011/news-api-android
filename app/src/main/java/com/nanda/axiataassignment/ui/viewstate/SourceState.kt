package com.nanda.axiataassignment.ui.viewstate

import com.nanda.axiataassignment.data.model.SourceResponse

sealed class SourceState {
    object Idle : SourceState()
    object Loading : SourceState()
    data class News(val news: SourceResponse) : SourceState()
    data class Error(val error: String?) : SourceState()
}