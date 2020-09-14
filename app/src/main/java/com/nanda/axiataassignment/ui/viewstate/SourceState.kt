package com.nanda.axiataassignment.ui.viewstate

import com.nanda.axiataassignment.data.model.SourceResponse

data class SourceViewState(
    val category: String? = null,
    val page: Int = 0
)

sealed class SourceState {
    object Idle : SourceState()
    object Loading : SourceState()
    data class Success(val data: SourceResponse) : SourceState()
    data class Error(val error: String?) : SourceState()
}