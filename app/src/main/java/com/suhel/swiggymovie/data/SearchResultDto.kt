package com.suhel.swiggymovie.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResultDto(
    @SerialName("Title")
    val title: String,
    @SerialName("Year")
    val year: String,
    @SerialName("imdbID")
    val imdbId: String,
    @SerialName("Type")
    val type: String,
    @SerialName("Poster")
    val posterUrl: String,
)