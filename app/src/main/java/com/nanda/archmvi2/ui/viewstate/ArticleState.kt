package com.nanda.archmvi2.ui.viewstate

import com.nanda.archmvi2.data.model.ArticleResponse

data class ArticleViewState(
    val sourceId: String? = null,
    val page: Int = 1
)

sealed class ArticleState {
    object Idle : ArticleState()
    object Loading : ArticleState()
    data class Success(val article: ArticleResponse) : ArticleState()
    data class Error(val error: String?) : ArticleState()
}