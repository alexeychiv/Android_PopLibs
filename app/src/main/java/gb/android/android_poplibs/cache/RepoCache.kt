package gb.android.android_poplibs.cache

import gb.android.android_poplibs.model.GithubRepoModel
import gb.android.android_poplibs.model.GithubUserModel
import io.reactivex.rxjava3.core.Single

interface RepoCache {

    fun getRepos(githubUserModel: GithubUserModel): Single<List<GithubRepoModel>>

    fun cacheRepos(repos: List<GithubRepoModel>): Single<List<GithubRepoModel>>

}