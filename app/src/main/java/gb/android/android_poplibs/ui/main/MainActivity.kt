package gb.android.android_poplibs.ui.main

import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.github.terrakok.cicerone.androidx.AppNavigator
import gb.android.android_poplibs.App
import gb.android.android_poplibs.R
import gb.android.android_poplibs.screens.AppScreens
import gb.android.android_poplibs.ui.base.BackButtonListener
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(R.layout.activity_main), MainView {

    private val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter { MainPresenter(App.instance.router) }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigationHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }

        presenter.backPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_main_item_rxJavaDemo -> {
                App.instance.router.navigateTo(AppScreens.rxJavaDemoScreen())
                true
            }
            R.id.menu_main_item_pngToJpegConverter -> {
                App.instance.router.navigateTo(AppScreens.PngToJpgConverterScreen())
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}