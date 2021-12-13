package gb.android.android_poplibs.ui.userdetails

import gb.android.android_poplibs.model.GithubRepoModel
import gb.android.android_poplibs.model.GithubUserModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle


interface UserDetailsView : MvpView {

    @AddToEndSingle
    fun updateUser(githubUserModel: GithubUserModel)

    @AddToEndSingle
    fun updateRepoList(repos: List<GithubRepoModel>)


    @AddToEndSingle
    fun showLoading()

    @AddToEndSingle
    fun hideLoading()

}