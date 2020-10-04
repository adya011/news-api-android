package com.nanda.archmvi2.ui.intent

sealed class ArticleIntent {
    object FetchArticle : ArticleIntent()
}