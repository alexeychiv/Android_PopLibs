package gb.android.android_poplibs.cache

import gb.android.android_poplibs.cache.db.AppDatabase
import gb.android.android_poplibs.cache.db.model.RoomGithubRepo
import gb.android.android_poplibs.model.GithubRepoModel
import gb.android.android_poplibs.model.GithubRepoOwner
import gb.android.android_poplibs.model.GithubUserModel
import io.reactivex.rxjava3.core.Single

class RoomRepoCache(
    private val db: AppDatabase,
) : RepoCache {

    override fun getRepos(githubUserModel: GithubUserModel): Single<List<GithubRepoModel>> {
        return db.repositoryDao.getByUserId(githubUserModel.id)
            .map { repos ->
                repos.map {
                    GithubRepoModel(
                        it.id,
                        it.name,
                        GithubRepoOwner(it.userId),
                        it.forksCount
                    )
                }
            }
    }

    override fun cacheRepos(repos: List<GithubRepoModel>): Single<List<GithubRepoModel>> {
        val dbRepos = repos.map {
            RoomGithubRepo(
                it.id,
                it.name,
                it.owner.id,
                it.forksCount
            )
        }
        return db.repositoryDao.insert(dbRepos).toSingle { repos }
    }

}