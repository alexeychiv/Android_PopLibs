package gb.android.android_poplibs.cache

import gb.android.android_poplibs.model.GithubUserModel
import io.reactivex.rxjava3.core.Single

interface UsersCache {

    fun getUsers(): Single<List<GithubUserModel>>

    fun cacheUsers(users: List<GithubUserModel>): Single<List<GithubUserModel>>

}