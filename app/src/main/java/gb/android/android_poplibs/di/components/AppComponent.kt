package gb.android.android_poplibs.di.components

import dagger.Component
import gb.android.android_poplibs.di.modules.*
import gb.android.android_poplibs.ui.main.MainActivity
import gb.android.android_poplibs.ui.main.MainPresenter
import gb.android.android_poplibs.ui.repodetails.RepoDetailsPresenter
import gb.android.android_poplibs.ui.repodetails.RepoDetailsPresenterFactory
import gb.android.android_poplibs.ui.userdetails.UserDetailsPresenter
import gb.android.android_poplibs.ui.userdetails.UserDetailsPresenterFactory
import gb.android.android_poplibs.ui.users.UsersPresenter
import javax.inject.Singleton


@Singleton
@Component(
        modules = [
            CacheModule::class,
            CiceroneModule::class,
            ContextModule::class,
            NetworkModule::class,
            UsersModule::class
        ]
)
interface AppComponent {

    fun mainPresenter(): MainPresenter

    fun usersPresenter(): UsersPresenter

    fun userDetailsPresenterFactory(): UserDetailsPresenterFactory

    fun repoDetailsPresenterFactory(): RepoDetailsPresenterFactory

    fun inject(mainActivity: MainActivity)

}