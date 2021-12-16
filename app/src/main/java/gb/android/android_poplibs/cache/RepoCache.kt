package gb.android.android_poplibs.cache

import gb.android.android_poplibs.model.GithubRepoModel
import gb.android.android_poplibs.model.GithubUserModel

interface RepoCache {

    fun getRepos(githubUserModel: GithubUserModel): List<GithubRepoModel>

    fun cacheRepos(repos: List<GithubRepoModel>)

}