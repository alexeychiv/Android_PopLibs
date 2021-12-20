package gb.android.android_poplibs.di.components

import dagger.Subcomponent
import gb.android.android_poplibs.di.modules.RepoDetailsModule
import gb.android.android_poplibs.di.scope.RepoDetailsScope
import gb.android.android_poplibs.ui.repodetails.RepoDetailsPresenterFactory


@RepoDetailsScope
@Subcomponent(
    modules = [
        RepoDetailsModule::class
    ]
)
interface RepoDetailsSubcomponent {

    fun repoDetailsPresenterFactory(): RepoDetailsPresenterFactory

}