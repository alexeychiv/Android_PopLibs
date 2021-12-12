package gb.android.android_poplibs.remote

import gb.android.android_poplibs.model.GithubUserModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface RetrofitService {

    @GET("/users")
    fun getUsers(): Single<List<GithubUserModel>>

}