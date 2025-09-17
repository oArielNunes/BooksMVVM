package data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * RESPONSE PRINCIPAL - Mapeia JSON response completo da API
 */

@JsonClass(generateAdapter = true)
data class BookBodyResponse (
    @Json(name = "results")
    val bookResults: List<BookResultsResponse>
)