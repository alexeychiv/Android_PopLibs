package gb.android.android_poplibs.ui.users

import com.github.terrakok.cicerone.Router
import gb.android.android_poplibs.App
import gb.android.android_poplibs.domain.GithubUsersRepository
import gb.android.android_poplibs.model.GithubUserModel
import gb.android.android_poplibs.screens.AppScreens
import moxy.MvpPresenter

class UsersPresenter(
    private val router: Router,
    private val usersRepository: GithubUsersRepository
) : MvpPresenter<UsersView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    fun onUserClicked(githubUserModel: GithubUserModel) {
        App.instance.router.navigateTo(AppScreens.userScreen(githubUserModel))
    }

    private fun loadData() {
        viewState.updateList(usersRepository.getUsers())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}