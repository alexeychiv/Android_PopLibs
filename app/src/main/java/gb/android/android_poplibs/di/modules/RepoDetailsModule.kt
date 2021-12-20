package gb.android.android_poplibs.di.modules

import dagger.Module
import dagger.Provides
import gb.android.android_poplibs.App
import gb.android.android_poplibs.di.scope.RepoDetailsScope
import gb.android.android_poplibs.di.scope.containers.RepoDetailsScopeContainer


@Module
abstract class RepoDetailsModule {

    companion object {

        @RepoDetailsScope
        @Provides
        fun scopeContainer(app: App): RepoDetailsScopeContainer = app

    }

}