package com.suhel.swiggymovie.usecases

import android.util.Log
import com.suhel.swiggymovie.OMDB_API_KEY
import com.suhel.swiggymovie.data.Api
import com.suhel.swiggymovie.data.toDomain
import com.suhel.swiggymovie.model.MovieDetailModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMovieDetailsUseCase @Inject constructor(
    private val api: Api
) {
    suspend fun invoke(imdbId: String): Result<MovieDetailModel> =
        withContext(Dispatchers.IO) {
            try {
                Result.success(
                    api.getMovieDetails(OMDB_API_KEY, imdbId).toDomain()
                )
            } catch (e: Exception) {
                Log.e("GetSearchResults", "Some error occured", e)
                Result.failure(e)
            }
        }
}
