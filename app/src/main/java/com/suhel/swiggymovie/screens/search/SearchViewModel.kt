package com.suhel.swiggymovie.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suhel.swiggymovie.usecases.GetSearchResultsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchResultsUseCase: GetSearchResultsUseCase
) : ViewModel() {
    private val searchQuery = MutableStateFlow<String>("")

    val uiState = searchQuery.debounce { if (it.isBlank()) 0L else 500L }
        .mapLatest { getSearchResultsUseCase.invoke(it) }
        .combine(searchQuery) { results, query ->
            SearchUiState(
                query = query,
                results = results.getOrNull().orEmpty(),
                error = results.exceptionOrNull()?.message
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SearchUiState()
        )

    fun onSearchQueryUpdated(query: String) {
        searchQuery.value = query
    }
}
