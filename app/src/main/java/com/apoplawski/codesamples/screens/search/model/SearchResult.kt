package com.apoplawski.codesamples.screens.search.model


sealed interface ViewState {
    object Loading : ViewState
    object Error : ViewState
    object NoResults : ViewState
    data class SearchResultsFetched(val results: List<SearchResult>) : ViewState
}

data class SearchResult(
    val id: Int,
    val title: String,
    val subtitle: String,
)