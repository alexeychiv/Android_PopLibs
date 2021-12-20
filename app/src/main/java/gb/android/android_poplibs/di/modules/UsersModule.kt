package gb.android.android_poplibs.di.modules

import dagger.Binds
import dagger.Module
import dagger.Provides
import gb.android.android_poplibs.App
import gb.android.android_poplibs.cache.RoomUsersCache
import gb.android.android_poplibs.cache.UsersCache
import gb.android.android_poplibs.cache.db.AppDatabase
import gb.android.android_poplibs.di.scope.UsersScope
import gb.android.android_poplibs.di.scope.containers.UsersScopeContainer
import gb.android.android_poplibs.domain.GithubUsersRepository
import gb.android.android_poplibs.domain.GithubUsersRepositoryImpl


@Module
abstract class UsersModule {

    @UsersScope
    @Binds
    abstract fun usersRepository(impl: GithubUsersRepositoryImpl): GithubUsersRepository

    companion object {

        @UsersScope
        @Provides
        fun userCache(db: AppDatabase): UsersCache {
            return RoomUsersCache(db)
        }

        @UsersScope
        @Provides
        fun scopeContainer(app: App): UsersScopeContainer = app
    }

}