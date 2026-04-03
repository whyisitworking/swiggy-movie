package com.suhel.swiggymovie.screens.search

import com.suhel.swiggymovie.model.SearchResultModel

data class SearchUiState(
    val query: String = "",
    val results: List<SearchResultModel> = emptyList(),
    val error: String? = null
)
