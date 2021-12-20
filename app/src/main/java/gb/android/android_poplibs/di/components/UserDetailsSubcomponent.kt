package gb.android.android_poplibs.di.components

import dagger.Subcomponent
import gb.android.android_poplibs.di.modules.UserDetailsModule
import gb.android.android_poplibs.di.scope.UserDetailsScope
import gb.android.android_poplibs.ui.userdetails.UserDetailsPresenterFactory

@UserDetailsScope
@Subcomponent(
    modules = [
        UserDetailsModule::class
    ]
)
interface UserDetailsSubcomponent {

    fun userDetailsPresenterFactory(): UserDetailsPresenterFactory

    fun repoDetailsSubcomponent(): RepoDetailsSubcomponent
}