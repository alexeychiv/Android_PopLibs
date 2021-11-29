package gb.android.android_poplibs.ui.users

import gb.android.android_poplibs.databinding.ItemUserBinding
import gb.android.android_poplibs.domain.GithubUsersRepository
import gb.android.android_poplibs.model.GithubUserModel
import gb.android.android_poplibs.ui.base.IListPresenter
import moxy.MvpPresenter

class UsersPresenter(
    private val usersRepository: GithubUsersRepository
) : MvpPresenter<UsersView>() {

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()

        usersListPresenter.itemClickListener = {} //TODO
    }

    private fun loadData() {
        usersListPresenter.users.addAll(usersRepository.getUsers())
        viewState.updateList()
    }


    class UsersListPresenter: IListPresenter<UserItemView> {

        val users = mutableListOf<GithubUserModel>()

        override var itemClickListener: () -> Unit = {}

        override fun getCount(): Int = users.size

        override fun bindView(view: UserItemView) {
            view.setLogin(users[view.pos].login)
        }

    }

}