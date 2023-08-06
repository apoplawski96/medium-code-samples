package com.apoplawski.codesamples.screens.search.data

import kotlinx.coroutines.delay
import java.io.IOException

data class SearchResultSchema(
    val id: Int,
    val title: String,
    val subtitle: String,
)

class FakeSearchAPI {

    suspend fun getResults(searchTerm: String): List<SearchResultSchema> {
        delay(700)
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

//val results = mutableListOf<SearchResultSchema>()
//for (i in 0 until 20) {
//    results.add(
//        SearchResultSchema(
//            id = i,
//            title = "Title of result $i",
//            subtitle = "Subtitle of result $i"
//        )
//    )
//}
