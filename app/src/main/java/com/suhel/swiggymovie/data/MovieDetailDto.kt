package com.suhel.swiggymovie.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailDto(
    @SerialName("Title")
    val title: String,
    @SerialName("Year")
    val year: String,
    @SerialName("Type")
    val type: String,
    @SerialName("Poster")
    val posterUrl: String,
)
