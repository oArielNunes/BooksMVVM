package presentation.books

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import booksmvvm.R
import booksmvvm.data.ApiService
import data.repository.BooksApiDataSource
import presentation.base.BaseActivity
import presentation.details.BooksDetailsActivity
import kotlinx.android.synthetic.main.activity_books.*
import kotlinx.android.synthetic.main.include_toolbar.*

/**
 * ACTIVITY PRINCIPAL - Exibe lista de livros
 */

class BooksActivity : BaseActivity() {

    private lateinit var viewModel: BooksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        setupToolbar(toolbarMain, R.string.books_title)
        // Configuração pull-to-refresh
        refreshLayout.setColorSchemeResources(R.color.colorAccent)
        refreshLayout.setOnRefreshListener {
            viewModel.getBooks()
        }
        // Inicializa ViewModel com Factory
        viewModel = ViewModelProvider(
            viewModelStore,
            BooksViewModel.ViewModelFactory(
                BooksApiDataSource(
                    ApiService.service
                )
            )
        ).get(BooksViewModel::class.java)
        // Carrega dados apenas na primeira criação
        if (savedInstanceState == null) {
            viewModel.getBooks()
        }
        // Observa mudanças na lista de livros
        viewModel.booksLiveData.observe(this, Observer {
            it?.let { books ->
                with(recyclerBooks) {
                    layoutManager =
                        LinearLayoutManager(this@BooksActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = BooksAdapter(books) { book ->
                        val intent = BooksDetailsActivity.getStartIntent(
                            this@BooksActivity,
                            book.title,
                            book.description
                        )
                        this@BooksActivity.startActivity(intent)
                    }
                }
            }
        })
        // Observa mudanças de estado da UI
        viewModel.viewFlipperLiveData.observe(this, Observer {
            it?.let { viewFlipper ->
            refreshLayout.isRefreshing = false
                viewFlipperBooks.displayedChild = viewFlipper.first

                viewFlipper.second?.let { errorMessageResId ->
                    textViewError.text = getString(errorMessageResId)
                }
            }
        })
    }

    private fun removeObserve() {
        viewModel.booksLiveData.removeObservers(this)
        viewModel.viewFlipperLiveData.removeObservers(this)
    }

    override fun onResume() {
        super.onResume()
        shimmerFrameLayout.startShimmerAnimation()
    }

    override fun onPause() {
        shimmerFrameLayout.stopShimmerAnimation()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        removeObserve()
    }
}
