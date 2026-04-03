package com.suhel.swiggymovie.data

import com.suhel.swiggymovie.model.MovieDetailModel
import com.suhel.swiggymovie.model.SearchResultModel

fun SearchResultDto.toDomain(): SearchResultModel =
    SearchResultModel(
        imdbId = imdbId,
        title = title,
        year = year,
        posterUrl = posterUrl
    )

fun MovieDetailDto.toDomain(): MovieDetailModel =
    MovieDetailModel(
        title = title,
        year = year,
        type = type,
        posterUrl = posterUrl
    )
