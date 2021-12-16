package gb.android.android_poplibs.cache

import gb.android.android_poplibs.db.AppDatabase
import gb.android.android_poplibs.db.model.RoomGithubRepo
import gb.android.android_poplibs.model.GithubRepoModel
import gb.android.android_poplibs.model.GithubRepoOwner
import gb.android.android_poplibs.model.GithubUserModel

class RoomRepoCache(
    private val db: AppDatabase,
) : RepoCache {

    override fun getRepos(githubUserModel: GithubUserModel): List<GithubRepoModel> {
        return db.repositoryDao.getByUserId(githubUserModel.id)
            .map {
                GithubRepoModel(
                    it.id,
                    it.name,
                    GithubRepoOwner(it.userId),
                    it.forksCount
                )
            }
    }

    override fun cacheRepos(repos: List<GithubRepoModel>) {
        val dbRepos = repos.map {
            RoomGithubRepo(it.id, it.name, it.owner.id, it.forksCount)
        }
        db.repositoryDao.insert(dbRepos)
    }

}