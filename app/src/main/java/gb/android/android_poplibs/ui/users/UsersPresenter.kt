package gb.android.android_poplibs.ui.users

import android.util.Log
import com.github.terrakok.cicerone.Router
import gb.android.android_poplibs.App
import gb.android.android_poplibs.databinding.ItemUserBinding
import gb.android.android_poplibs.domain.GithubUsersRepository
import gb.android.android_poplibs.model.GithubUserModel
import gb.android.android_poplibs.screens.AppScreens
import gb.android.android_poplibs.ui.base.BackButtonListener
import gb.android.android_poplibs.ui.base.IItemView
import gb.android.android_poplibs.ui.base.IListPresenter
import moxy.MvpPresenter

class UsersPresenter(
    private val router: Router,
    private val usersRepository: GithubUsersRepository
) : MvpPresenter<UsersView>() {

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        usersListPresenter.itemClickListener = {
            Log.d("BLAH", "usersListPresenter.itemClickListener ${it.pos}")
            App.instance.router.navigateTo(AppScreens.userScreen(usersListPresenter.users[it.pos]))
        }

        loadData()
    }

    private fun loadData() {
        usersListPresenter.users.addAll(usersRepository.getUsers())
        viewState.updateList()
    }


    class UsersListPresenter: IListPresenter<UserItemView> {

        val users = mutableListOf<GithubUserModel>()

        override var itemClickListener: (IItemView) -> Unit = {
            Log.d("BLAH", "usersListPresenter.itemClickListener __ ${it.pos}")
        }

        override fun getCount(): Int = users.size

        override fun bindView(view: UserItemView) {
            view.setLogin(users[view.pos].login)
        }

    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}