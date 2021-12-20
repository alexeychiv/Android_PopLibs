package gb.android.android_poplibs.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import gb.android.android_poplibs.App
import javax.inject.Singleton


@Module
class AppModule(private val app: App) {

    @Singleton
    @Provides
    fun context(): Context {
        return app
    }

    @Singleton
    @Provides
    fun app(): App {
        return app
    }
}