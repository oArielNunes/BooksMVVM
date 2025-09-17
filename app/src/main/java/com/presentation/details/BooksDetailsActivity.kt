
package booksmvvm.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import booksmvvm.R
import booksmvvm.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_books_details.*
import kotlinx.android.synthetic.main.include_toolbar.*

/**
 * ACTIVITY DE DETALHES - Exibe informações detalhadas de um livro

 */

class BooksDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_details)

        setupToolbar(toolbarMain, R.string.books_details)
        // Obtém dados da Intent
        bookDetailsTitle.text = intent.getStringExtra(EXTRA_TITLE)
        bookDetailsDescription.text = intent.getStringExtra(EXTRA_DESCRIPTION)
    }

    companion object {
        private const val EXTRA_TITLE = "EXTRA_TITLE"
        private const val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"

            fun getStartIntent(context: Context, title: String, description: String): Intent{
                return Intent(context, BooksDetailsActivity::class.java ).apply {
                    putExtra(EXTRA_TITLE, title)
                    putExtra(EXTRA_DESCRIPTION, description)
                }
            }
    }
}
