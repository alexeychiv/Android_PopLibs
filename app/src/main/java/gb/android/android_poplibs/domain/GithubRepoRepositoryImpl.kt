package gb.android.android_poplibs.domain

import gb.android.android_poplibs.model.GithubRepoModel
import gb.android.android_poplibs.remote.RetrofitService
import io.reactivex.rxjava3.core.Single

class GithubRepoRepositoryImpl(
    private val retrofitService: RetrofitService,
) : GithubRepoRepository {

    override fun getRepos(reposUrl: String): Single<List<GithubRepoModel>> {
        return retrofitService.getRepos(reposUrl)
    }

}