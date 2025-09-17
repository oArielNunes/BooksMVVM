package data.response

import data.model.Book
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * RESPONSE DE DETALHES - Dados específicos de cada livro
 */

@JsonClass(generateAdapter = true)
data class BooksDetailsResponse (
    @Json(name = "title")
    val title: String,
    @Json(name = "author")
    val author: String,
    @Json(name = "description")
    val description: String
) {
    // Converte response da API para modelo de domínio
    fun getBookModel() = Book(
        title = this.title,
        author = this.author,
        description = this.description
    )
}
