package gb.android.android_poplibs.domain

import gb.android.android_poplibs.model.GithubUserModel
import gb.android.android_poplibs.remote.RetrofitService
import io.reactivex.rxjava3.core.Single

class GithubUsersRepositoryImpl(
    private val retrofitService: RetrofitService
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
        return retrofitService.getUsers()
    }

}