package gb.android.android_poplibs.cache

import gb.android.android_poplibs.cache.db.AppDatabase
import gb.android.android_poplibs.cache.db.model.RoomGithubUser
import gb.android.android_poplibs.model.GithubUserModel
import io.reactivex.rxjava3.core.Single

class RoomUsersCache(
    private val db: AppDatabase,
) : UsersCache {

    override fun getUsers(): Single<List<GithubUserModel>> {
        return db.userDao.getAll().map {roomUsers->
            roomUsers.map { roomGithubUser ->
                GithubUserModel(
                    roomGithubUser.id,
                    roomGithubUser.login,
                    roomGithubUser.avatarUrl,
                    roomGithubUser.reposUrl
                )
            }
        }
    }

    override fun cacheUsers(users: List<GithubUserModel>) : Single<List<GithubUserModel>> {
        val roomUsers = users.map { githubUserModel ->
            RoomGithubUser(
                githubUserModel.id,
                githubUserModel.login,
                githubUserModel.avatarUrl,
                githubUserModel.reposUrl
            )
        }
        return db.userDao.insert(roomUsers).toSingle { users }
    }

}