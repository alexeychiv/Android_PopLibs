package gb.android.android_poplibs.cache

import gb.android.android_poplibs.model.GithubUserModel

interface UsersCache {

    fun getUsers(): List<GithubUserModel>

    fun cacheUsers(users: List<GithubUserModel>)

}