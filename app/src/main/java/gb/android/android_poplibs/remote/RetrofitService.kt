package gb.android.android_poplibs.remote

import gb.android.android_poplibs.model.GithubRepoModel
import gb.android.android_poplibs.model.GithubUserModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface RetrofitService {

    @GET("/users")
    fun getUsers(): Single<List<GithubUserModel>>

    @GET
    fun getRepos(@Url reposUrl: String): Single<List<GithubRepoModel>>

}