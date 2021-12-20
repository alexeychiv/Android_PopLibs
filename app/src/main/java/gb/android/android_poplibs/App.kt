package gb.android.android_poplibs

import android.app.Application
import gb.android.android_poplibs.di.components.*
import gb.android.android_poplibs.di.modules.AppModule
import gb.android.android_poplibs.di.scope.containers.RepoDetailsScopeContainer
import gb.android.android_poplibs.di.scope.containers.UserDetailsScopeContainer
import gb.android.android_poplibs.di.scope.containers.UsersScopeContainer

class App : Application(), UsersScopeContainer, UserDetailsScopeContainer,
    RepoDetailsScopeContainer {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
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


    //==========================================================================================
    //SUBCOMPONENTS

    var usersSubcomponent: UsersSubcomponent? = null
    var userDetailsSubcomponent: UserDetailsSubcomponent? = null
    var repoDetailsSubcomponent: RepoDetailsSubcomponent? = null

    override fun initUsersSubcomponent() {
        appComponent.usersSubcomponent().also { usersSubcomponent = it }
    }

    override fun destroyUsersSubcomponent() {
        usersSubcomponent = null
    }

    override fun initUserDetailsSubcomponent() {
        appComponent.usersSubcomponent().userDetailsSubcomponent().also {
            userDetailsSubcomponent = it
        }
    }

    override fun destroyUserDetailsSubcomponent() {
        userDetailsSubcomponent = null
    }

    override fun initRepoDetailsSubcomponent() {
        appComponent.usersSubcomponent().userDetailsSubcomponent().repoDetailsSubcomponent().also {
            repoDetailsSubcomponent = it
        }
    }

    override fun destroyRepoDetailsSubcomponent() {
        repoDetailsSubcomponent = null
    }

}