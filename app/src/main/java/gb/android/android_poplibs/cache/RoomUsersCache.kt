package gb.android.android_poplibs.cache

import gb.android.android_poplibs.db.AppDatabase
import gb.android.android_poplibs.db.model.RoomGithubUser
import gb.android.android_poplibs.model.GithubUserModel

class RoomUsersCache(
    private val db: AppDatabase,
) : UsersCache {

    override fun getUsers(): List<GithubUserModel> {
        return db.userDao.getAll().map { roomModel ->
            GithubUserModel(
                roomModel.id,
                roomModel.login,
                roomModel.avatarUrl,
                roomModel.reposUrl
            )
        }
    }

    override fun cacheUsers(users: List<GithubUserModel>) {
        val roomUsers = users.map { user ->
            RoomGithubUser(user.id, user.login, user.avatarUrl, user.reposUrl)
        }
        db.userDao.insert(roomUsers)
    }

}