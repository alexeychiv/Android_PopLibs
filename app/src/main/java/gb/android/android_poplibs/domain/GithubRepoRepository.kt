package gb.android.android_poplibs.domain

import gb.android.android_poplibs.model.GithubRepoModel
import io.reactivex.rxjava3.core.Single

interface GithubRepoRepository {

    fun getRepos(reposUrl: String): Single<List<GithubRepoModel>>

}