package booksmvvm.presentation.books

import androidx.lifecycle.*
import booksmvvm.R
import data.BooksResult
import data.model.Book
import data.repository.BooksRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * VIEWMODEL - Gerencia dados da UI e sobrevive a mudanças de configuração
 */

class BooksViewModel(private val dataSource: BooksRepository) : ViewModel() {
    // LiveData para lista de livros
    private val _booksLiveData = MutableLiveData<List<Book>>()
    val booksLiveData: LiveData<List<Book>> = _booksLiveData

    // LiveData para controle do ViewFlipper (estado da UI)
    val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    /**
     * Obtém livros da fonte de dados
     */
    fun getBooks() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
            withContext(Dispatchers.Main) {
                dataSource.getBooks { result: BooksResult ->
                    when (result) {
                        is BooksResult.Success -> {
                            _booksLiveData.value = result.books
                            viewFlipperLiveData.value = Pair(VIEW_FLIPPER_BOOKS, null)
                        }
                        is BooksResult.ApiError -> {
                            if (result.statusCode == 401) {
                                viewFlipperLiveData.value =
                                    Pair(VIEW_FLIPPER_ERROR, R.string.books_error_401)
                            } else {
                                viewFlipperLiveData.value =
                                    Pair(VIEW_FLIPPER_ERROR, R.string.books_error_400_generic)
                            }
                        }
                        is BooksResult.ServerError -> {
                            viewFlipperLiveData.value =
                                Pair(VIEW_FLIPPER_ERROR, R.string.books_error_500_generic)
                        }
                    }
                }
            }
            } catch (e: Exception) {
                viewFlipperLiveData.value =
                    Pair(VIEW_FLIPPER_ERROR, R.string.books_error_500_generic)
            }
        }
    }

    /**
     * Factory pattern para criação do ViewModel com dependências
     */
    class ViewModelFactory(private val dataSource: BooksRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BooksViewModel::class.java)) {
                return modelClass.getConstructor(BooksRepository::class.java)
                    .newInstance(dataSource)
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    companion object {
        private const val VIEW_FLIPPER_BOOKS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }
}