package gb.android.android_poplibs.domain

import gb.android.android_poplibs.cache.RepoCache
import gb.android.android_poplibs.model.GithubRepoModel
import gb.android.android_poplibs.model.GithubUserModel
import gb.android.android_poplibs.remote.RetrofitService
import gb.android.android_poplibs.remote.connectivity.NetworkStatus
import io.reactivex.rxjava3.core.Single

class GithubRepoRepositoryImpl(
    private val networkStatus: NetworkStatus,
    private val retrofitService: RetrofitService,
    private val repoCache: RepoCache,
) : GithubRepoRepository {

    override fun getRepos(githubUserModel: GithubUserModel): Single<List<GithubRepoModel>> {
        return if (networkStatus.isOnline()) {
            retrofitService.getRepos(githubUserModel.reposUrl)
                .flatMap(repoCache::cacheRepos)
        } else {
            repoCache.getRepos(githubUserModel)
        }
    }

}