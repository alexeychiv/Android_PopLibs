package gb.android.android_poplibs.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import gb.android.android_poplibs.App
import gb.android.android_poplibs.R
import gb.android.android_poplibs.ui.base.BackButtonListener
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(R.layout.activity_main), MainView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter {
        App.instance.appComponent.mainPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.instance.appComponent.inject(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }

        presenter.backPressed()
    }


    //=====================================================================================
    //MENU

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_main_item_rxJavaDemo -> {
                presenter.openRxJavaDemo()
                true
            }
            R.id.menu_main_item_pngToJpegConverter -> {
                presenter.openPngToJpegConverter()
                true
            }
            R.id.menu_main_item_okHTTP -> {
                presenter.openOkHTTPDemo()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}