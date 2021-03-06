package gb.android.android_poplibs.domain

import gb.android.android_poplibs.model.GithubUserModel
import io.reactivex.rxjava3.core.Single

interface GithubUsersRepository {

    fun getUsers(): Single<List<GithubUserModel>>

}