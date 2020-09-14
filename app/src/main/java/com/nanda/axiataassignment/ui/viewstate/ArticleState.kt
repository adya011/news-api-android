package com.nanda.axiataassignment.ui.viewstate

import com.nanda.axiataassignment.data.model.ArticleResponse

data class ArticleViewState(
    val sources: String? = null,
    val page: Int = 0
)

sealed class ArticleState {
    object Idle : ArticleState()
    object Loading : ArticleState()
    data class News(val article: ArticleResponse) : ArticleState()
    data class Error(val error: String?) : ArticleState()
}