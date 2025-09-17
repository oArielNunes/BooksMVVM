package presentation.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

/**
 * ACTIVITY BASE - Classe base para atividades com funcionalidades comuns
 */

open class BaseActivity: AppCompatActivity() {

    /**
     * Configura toolbar com título
     * toolbar Toolbar a ser configurada
     * titleIdRes Resource ID do título
     */

    protected fun setupToolbar(toolbar: Toolbar, titleIdRes: Int){
        toolbar.title = getString(titleIdRes)
        setSupportActionBar(toolbar)
    }
}