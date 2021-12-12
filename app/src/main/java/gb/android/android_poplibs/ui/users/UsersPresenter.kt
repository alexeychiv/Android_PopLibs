package gb.android.android_poplibs.ui.users

import android.util.Log
import com.github.terrakok.cicerone.Router
import gb.android.android_poplibs.App
import gb.android.android_poplibs.domain.GithubUsersRepositoryImpl
import gb.android.android_poplibs.model.GithubUserModel
import gb.android.android_poplibs.screens.AppScreens
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class UsersPresenter(
    private val router: Router,
    private val usersRepositoryImpl: GithubUsersRepositoryImpl
) : MvpPresenter<UsersView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    fun onUserClicked(githubUserModel: GithubUserModel) {
        App.instance.router.navigateTo(AppScreens.userScreen(githubUserModel))
    }

    private fun loadData() {
        usersRepositoryImpl.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                viewState.showLoading()
            }
            .subscribe(
                { users ->
                    viewState.updateList(users)
                    viewState.hideLoading()
                },
                { e ->
                    Log.e("Retrofit", "ERROR: Unable to receive users list!", e)
                    viewState.hideLoading()
                }
            )
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}