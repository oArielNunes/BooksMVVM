package data

import com.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * INTERFACE RETROFIT - Define endpoints da API NY Times
 * Configura parâmetros de query e métodos HTTP
 */

interface NYTServices {

    @GET("lists.json")
    fun getBooks(
        @Query("api-key") apiKey: String = "8vlpwrJxE6ua4gMjAjKXJOGk9mhIGztk",
        @Query("list") list: String = "hardcover-fiction"
    ): Call<BookBodyResponse>
}