package gb.android.android_poplibs.di.components

import dagger.Subcomponent
import gb.android.android_poplibs.di.modules.UsersModule
import gb.android.android_poplibs.di.scope.UsersScope
import gb.android.android_poplibs.ui.users.UsersPresenter


@UsersScope
@Subcomponent(
    modules = [
        UsersModule::class
    ]
)
interface UsersSubcomponent {

    fun usersPresenter(): UsersPresenter

    fun userDetailsSubcomponent(): UserDetailsSubcomponent

}