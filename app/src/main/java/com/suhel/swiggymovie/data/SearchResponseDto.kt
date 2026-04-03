package com.suhel.swiggymovie.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponseDto(
    @SerialName("Search")
    val results: List<SearchResultDto>,
)
