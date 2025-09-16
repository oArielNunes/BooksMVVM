package repository

import BooksResult

interface BooksRepository {

    suspend fun getBooks(booksResultCallback: (result: BooksResult) -> Unit)
}