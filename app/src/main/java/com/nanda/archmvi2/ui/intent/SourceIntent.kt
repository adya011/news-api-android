package com.nanda.archmvi2.ui.intent

sealed class SourceIntent {
    object FetchSources : SourceIntent()
}