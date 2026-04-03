package com.suhel.swiggymovie.data

import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/")
    suspend fun getSearchResults(
        @Query("apiKey") apiKey: String,
        @Query("s") query: String,
        @Query("page") pageNo: Int
    ): SearchResponseDto

    @GET("/")
    suspend fun getMovieDetails(
        @Query("apiKey") apiKey: String,
        @Query("i") imdbId: String
    ): MovieDetailDto
}
