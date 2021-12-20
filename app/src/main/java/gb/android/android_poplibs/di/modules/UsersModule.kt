package gb.android.android_poplibs.di.modules

import dagger.Module
import dagger.Provides
import gb.android.android_poplibs.cache.RepoCache
import gb.android.android_poplibs.cache.UsersCache
import gb.android.android_poplibs.domain.GithubRepoRepository
import gb.android.android_poplibs.domain.GithubRepoRepositoryImpl
import gb.android.android_poplibs.domain.GithubUsersRepository
import gb.android.android_poplibs.domain.GithubUsersRepositoryImpl
import gb.android.android_poplibs.remote.RetrofitService
import gb.android.android_poplibs.remote.connectivity.NetworkStatus
import javax.inject.Singleton


@Module
class UsersModule {


    @Singleton
    @Provides
    fun usersRepo(
            networkStatus: NetworkStatus,
            retrofitService: RetrofitService,
            usersCache: UsersCache
    ): GithubUsersRepository {
        return GithubUsersRepositoryImpl(networkStatus, retrofitService, usersCache)
    }

    @Singleton
    @Provides
    fun reposRepo(
            networkStatus: NetworkStatus,
            retrofitService: RetrofitService,
            repoCache: RepoCache
    ): GithubRepoRepository {
        return GithubRepoRepositoryImpl(networkStatus, retrofitService, repoCache)
    }
}