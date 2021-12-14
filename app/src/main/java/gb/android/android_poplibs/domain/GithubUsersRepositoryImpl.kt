package gb.android.android_poplibs.domain

import gb.android.android_poplibs.db.AppDatabase
import gb.android.android_poplibs.db.model.RoomGithubUser
import gb.android.android_poplibs.model.GithubUserModel
import gb.android.android_poplibs.remote.RetrofitService
import gb.android.android_poplibs.remote.connectivity.NetworkStatus
import io.reactivex.rxjava3.core.Single

class GithubUsersRepositoryImpl(
    private val networkStatus: NetworkStatus,
    private val retrofitService: RetrofitService,
    private val db: AppDatabase,
) : GithubUsersRepository {

//    private val users = listOf(
//        GithubUserModel("user1"),
//        GithubUserModel("user2"),
//        GithubUserModel("user3"),
//        GithubUserModel("user4"),
//        GithubUserModel("user5"),
//        GithubUserModel("user6"),
//        GithubUserModel("user7"),
//    )

//    fun getUsers(): @NonNull Observable<List<GithubUserModel>> {
//        return Observable.just(users)
//    }

    override fun getUsers(): Single<List<GithubUserModel>> {
        return if (networkStatus.isOnline()) {
            retrofitService.getUsers()
                .flatMap { users ->
                    Single.fromCallable {
                        val roomUsers = users.map { user ->
                            RoomGithubUser(user.id, user.login, user.avatarUrl, user.reposUrl)
                        }
                        db.userDao.insert(roomUsers)
                        users
                    }
                }
        } else {
            Single.fromCallable {
                db.userDao.getAll().map { roomModel ->
                    GithubUserModel(
                        roomModel.id,
                        roomModel.login,
                        roomModel.avatarUrl,
                        roomModel.reposUrl
                    )
                }
            }
        }
    }

}