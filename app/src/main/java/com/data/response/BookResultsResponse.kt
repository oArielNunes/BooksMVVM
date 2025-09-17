package data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * RESPONSE DE RESULTADOS - Lista de resultados de livros
 */

@JsonClass(generateAdapter = true)
data class BookResultsResponse (
    @Json(name = "book_details")
    val bookDetailResponses: List<BooksDetailsResponse>
)