package gb.android.android_poplibs

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import gb.android.android_poplibs.di.components.AppComponent
import gb.android.android_poplibs.di.components.DaggerAppComponent
import gb.android.android_poplibs.di.modules.ContextModule

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .contextModule(ContextModule(this))
                .build()
    }

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