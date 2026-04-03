package com.suhel.swiggymovie.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suhel.swiggymovie.usecases.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {
    private val imdbId: String = savedStateHandle["imdbId"]
        ?: throw IllegalArgumentException("No imdbID passed!")

    val uiState = flow {
        emit(getMovieDetailsUseCase.invoke(imdbId))
    }
        .map { result ->
            if (result.isSuccess) {
                DetailsUiState.Success(result.getOrThrow())
            } else {
                DetailsUiState.Failure(result.exceptionOrNull()?.message ?: "")
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DetailsUiState.Loading
        )
}
