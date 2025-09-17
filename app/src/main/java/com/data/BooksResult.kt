package data

import data.model.Book
/**
 * WRAPPER DE RESULTADOS - Padrão sealed class para encapsular estados da operação
 * Permite tratamento de sucesso/erro na UI
 */

sealed class BooksResult {
    class Success(val books: List<Book>) : BooksResult() //sucesso com Dados
    class ApiError(val statusCode: Int) : BooksResult() // Erro da API
    object ServerError : BooksResult() //Erro de conexão/timeout
}