package gb.android.android_poplibs

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    val navigationHolder
        get() = cicerone.getNavigatorHolder()

    val router
        get() = cicerone.router

    companion object {
        private var _instance: App? = null
        val instance
            get() = _instance!!
    }

    override fun onCreate() {
        super.onCreate()
        _instance = this
    }

}