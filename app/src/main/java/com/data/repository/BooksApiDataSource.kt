package repository

import androidx.lifecycle.viewModelScope
import data.BooksResult
import data.NYTServices
import data.model.Book
import data.response.BookBodyResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksApiDataSource(private val service: NYTServices) : BooksRepository {

    override suspend fun getBooks(booksResultCallback: (result: BooksResult) -> Unit) {
        try {
            service.getBooks().enqueue(object : Callback<BookBodyResponse> {
                override fun onResponse(
                    call: Call<BookBodyResponse>,
                    response: Response<BookBodyResponse>
                ) {
                    when {
                        response.isSuccessful -> {
                            val books: MutableList<Book> = mutableListOf()

                            response.body()?.let { booksResponse ->
                                for (result in booksResponse.bookResults) {
                                    val book = result.bookDetailResponses[0].getBookModel()
                                    books.add(book)
                                }
                            }

                            booksResultCallback(BooksResult.Success(books))
                        }
                        else -> booksResultCallback(BooksResult.ApiError(response.code()))
                    }
                }

                override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                    booksResultCallback(BooksResult.ServerError)
                }
            })
        } catch (e:Exception){
            booksResultCallback(BooksResult.ServerError)
        }
    }
}