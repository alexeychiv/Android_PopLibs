package gb.android.android_poplibs.ui.users

import gb.android.android_poplibs.model.GithubUserModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle


interface UsersView : MvpView {

    @AddToEndSingle
    fun updateList(users: List<GithubUserModel>)

    @AddToEndSingle
    fun showLoading()

    @AddToEndSingle
    fun hideLoading()

}