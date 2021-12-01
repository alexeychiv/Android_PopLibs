package gb.android.android_poplibs.domain

import gb.android.android_poplibs.model.GithubUserModel

class GithubUsersRepository {

    private val users = listOf(
        GithubUserModel("user1"),
        GithubUserModel("user2"),
        GithubUserModel("user3"),
        GithubUserModel("user4"),
        GithubUserModel("user5"),
        GithubUserModel("user6"),
        GithubUserModel("user7"),
    )

    fun getUsers() : List<GithubUserModel> {
        return users
    }





}