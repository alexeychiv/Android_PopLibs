package gb.android.android_poplibs.ui.user

import gb.android.android_poplibs.model.GithubUserModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle


interface UserView : MvpView {

    @AddToEndSingle
    fun updateUser(githubUserModel: GithubUserModel)

}