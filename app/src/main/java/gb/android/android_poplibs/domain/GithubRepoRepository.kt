package gb.android.android_poplibs.domain

import gb.android.android_poplibs.model.GithubRepoModel
import gb.android.android_poplibs.model.GithubUserModel
import io.reactivex.rxjava3.core.Single

interface GithubRepoRepository {

    fun getRepos(githubUserModel: GithubUserModel): Single<List<GithubRepoModel>>

}