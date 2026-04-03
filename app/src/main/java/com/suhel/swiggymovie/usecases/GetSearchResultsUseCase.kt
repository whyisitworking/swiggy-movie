package com.suhel.swiggymovie.usecases

import android.util.Log
import com.suhel.swiggymovie.OMDB_API_KEY
import com.suhel.swiggymovie.data.Api
import com.suhel.swiggymovie.data.toDomain
import com.suhel.swiggymovie.model.SearchResultModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSearchResultsUseCase @Inject constructor(
    private val api: Api
) {
    suspend fun invoke(query: String, pageNo: Int = 1): Result<List<SearchResultModel>> =
        withContext(Dispatchers.IO) {
            if (query.isBlank()) {
                return@withContext Result.success(emptyList())
            }
            try {
                val response = api.getSearchResults(OMDB_API_KEY, query, pageNo)
                Result.success(
                    response.results.map { it.toDomain() }
                )
            } catch (e: Exception) {
                Log.e("GetSearchResults", "Some error occured", e)
                Result.failure(e)
            }
        }
}
