package com.suhel.swiggymovie.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.suhel.swiggymovie.model.SearchResultModel
import com.suhel.swiggymovie.usecases.GetSearchResultsUseCase
import javax.inject.Inject
import javax.inject.Singleton

class SearchResultsPagingSource(
    private val query: String,
    private val getSearchResultsUseCase: GetSearchResultsUseCase
) : PagingSource<Int, SearchResultModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchResultModel> {
        val currentKey = params.key ?: 1
        val response = getSearchResultsUseCase.invoke(query, currentKey)
        val results = response.getOrNull().orEmpty()

        return LoadResult.Page(
            data = results,
            prevKey = null,
            nextKey = if(results.isEmpty()) null else currentKey + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, SearchResultModel>): Int? {
        return null
    }

    @Singleton
    class Factory @Inject constructor(
        private val getSearchResultsUseCase: GetSearchResultsUseCase
    ) {
        fun create(query: String): PagingSource<Int, SearchResultModel> {
            return SearchResultsPagingSource(query, getSearchResultsUseCase)
        }
    }
}
