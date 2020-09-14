package com.nanda.axiataassignment.ui.viewstate

import com.nanda.axiataassignment.data.model.ArticleResponse

sealed class ArticleState {
    object Idle : ArticleState()
    object Loading : ArticleState()
    data class News(val article: ArticleResponse) : ArticleState()
    data class Error(val error: String?) : ArticleState()
}