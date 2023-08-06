package com.apoplawski.codesamples.screens.search.domain

import com.apoplawski.codesamples.screens.search.data.FakeSearchAPI
import com.apoplawski.codesamples.screens.search.model.SearchResult
import java.io.IOException

class GetSearchResults(private val searchAPI: FakeSearchAPI) {

    sealed interface Result {
        object Error : Result
        data class Success(val results: List<SearchResult>) : Result
    }

    suspend operator fun invoke(searchTerm: String): Result = try {
        val results = searchAPI.getResults(searchTerm).map { searchResultSchema ->
            SearchResult(
                id = searchResultSchema.id,
                title = "${searchResultSchema.title} for input $searchTerm",
                subtitle = searchResultSchema.subtitle,
            )
        }
        Result.Success(results = results)
    } catch (e: IOException) {
        Result.Error
    }
}