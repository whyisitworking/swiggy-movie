package com.suhel.swiggymovie.screens.details

import com.suhel.swiggymovie.model.MovieDetailModel

sealed interface DetailsUiState {
    data object Loading: DetailsUiState
    data class Success(val details: MovieDetailModel) : DetailsUiState
    data class Failure(val message: String) : DetailsUiState
}
