package gb.android.android_poplibs.ui.users

import gb.android.android_poplibs.model.GithubUserModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd


interface UsersView : MvpView {

    @AddToEnd
    fun updateList(users: List<GithubUserModel>)

}