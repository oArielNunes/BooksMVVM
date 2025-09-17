package repository

import BooksResult

/**
 * INTERFACE DO REPOSITÓRIO - Define o contrato para acesso a dados de livros
 * Padrão Repository: abstrai a fonte de dados (API, banco local, etc.)
 */

interface BooksRepository {

    suspend fun getBooks(booksResultCallback: (result: BooksResult) -> Unit)
}

/**
 * Obtém lista de livros de forma assíncrona
 * booksResultCallback Callback para retornar o resultado
 */