package gb.android.android_poplibs.di.components

import dagger.Component
import gb.android.android_poplibs.di.modules.AppModule
import gb.android.android_poplibs.di.modules.CiceroneModule
import gb.android.android_poplibs.di.modules.DbModule
import gb.android.android_poplibs.di.modules.NetworkModule
import gb.android.android_poplibs.ui.main.MainActivity
import gb.android.android_poplibs.ui.main.MainPresenter
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        DbModule::class,
        CiceroneModule::class,
        AppModule::class,
        NetworkModule::class,
    ]
)
interface AppComponent {

    fun mainPresenter(): MainPresenter

    fun usersSubcomponent(): UsersSubcomponent

    fun inject(mainActivity: MainActivity)

}