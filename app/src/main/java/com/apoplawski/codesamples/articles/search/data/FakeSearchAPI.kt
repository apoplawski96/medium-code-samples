package com.apoplawski.codesamples.articles.search.data

import kotlinx.coroutines.delay
import java.io.IOException

data class SearchResultSchema(
    val id: Int,
    val title: String,
    val subtitle: String,
)

class FakeSearchAPI {

    suspend fun getResults(searchTerm: String): List<SearchResultSchema> {
        delay(500)
        return when {
            searchTerm.length > 10 -> emptyList()
            searchTerm == "error" -> throw IOException()
            else -> generateFakeResults()
        }
    }

    private fun generateFakeResults(): List<SearchResultSchema> = List(size = 20) { i ->
        SearchResultSchema(
            id = i,
            title = "Title of result $i",
            subtitle = "Subtitle of result $i"
        )
    }
}