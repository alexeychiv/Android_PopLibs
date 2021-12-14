package gb.android.android_poplibs.domain

import gb.android.android_poplibs.db.AppDatabase
import gb.android.android_poplibs.db.model.RoomGithubRepo
import gb.android.android_poplibs.model.GithubRepoModel
import gb.android.android_poplibs.model.GithubRepoOwner
import gb.android.android_poplibs.model.GithubUserModel
import gb.android.android_poplibs.remote.RetrofitService
import gb.android.android_poplibs.remote.connectivity.NetworkStatus
import io.reactivex.rxjava3.core.Single

class GithubRepoRepositoryImpl(
    private val networkStatus: NetworkStatus,
    private val retrofitService: RetrofitService,
    private val db: AppDatabase,
) : GithubRepoRepository {

    override fun getRepos(githubUserModel: GithubUserModel): Single<List<GithubRepoModel>> {
        return if (networkStatus.isOnline()) {
            retrofitService.getRepos(githubUserModel.reposUrl)
                .flatMap { repos ->
                    Single.fromCallable {
                        val dbRepos = repos.map {
                            RoomGithubRepo(it.id, it.name, it.owner.id, it.forksCount)
                        }
                        db.repositoryDao.insert(dbRepos)
                        repos
                    }
                }
        } else {
            Single.fromCallable {
                db.repositoryDao.getByUserId(githubUserModel.id)
                    .map {
                        GithubRepoModel(
                            it.id,
                            it.name,
                            GithubRepoOwner(it.userId),
                            it.forksCount
                        )
                    }
            }
        }
    }

}