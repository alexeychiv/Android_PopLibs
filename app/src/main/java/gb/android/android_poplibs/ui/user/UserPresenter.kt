package gb.android.android_poplibs.ui.user

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter(
    private val router: Router,
    //private val usersRepository: GithubUsersRepository
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}
