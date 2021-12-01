package gb.android.android_poplibs.ui.user

import com.github.terrakok.cicerone.Router
import gb.android.android_poplibs.model.GithubUserModel
import moxy.MvpPresenter

class UserDetailsPresenter(
    private val router: Router,
    private val userModel: GithubUserModel
) : MvpPresenter<UserDetailsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.updateUser(userModel)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}
