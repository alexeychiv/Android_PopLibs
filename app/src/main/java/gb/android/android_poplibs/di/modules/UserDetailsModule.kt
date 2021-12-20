package gb.android.android_poplibs.di.modules

import dagger.Binds
import dagger.Module
import dagger.Provides
import gb.android.android_poplibs.App
import gb.android.android_poplibs.cache.RepoCache
import gb.android.android_poplibs.cache.RoomRepoCache
import gb.android.android_poplibs.cache.db.AppDatabase
import gb.android.android_poplibs.di.scope.UserDetailsScope
import gb.android.android_poplibs.di.scope.containers.UserDetailsScopeContainer
import gb.android.android_poplibs.domain.GithubRepoRepository
import gb.android.android_poplibs.domain.GithubRepoRepositoryImpl


@Module
abstract class UserDetailsModule {

    @UserDetailsScope
    @Binds
    abstract fun reposRepo(impl: GithubRepoRepositoryImpl): GithubRepoRepository

    companion object {

        @UserDetailsScope
        @Provides
        fun repoCache(db: AppDatabase): RepoCache {
            return RoomRepoCache(db)
        }

        @UserDetailsScope
        @Provides
        fun scopeContainer(app: App): UserDetailsScopeContainer = app
    }
}