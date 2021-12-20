package gb.android.android_poplibs.ui.users

import android.util.Log
import com.github.terrakok.cicerone.Router
import gb.android.android_poplibs.di.scope.containers.UsersScopeContainer
import gb.android.android_poplibs.domain.GithubUsersRepository
import gb.android.android_poplibs.model.GithubUserModel
import gb.android.android_poplibs.navigation.AppScreens
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class UsersPresenter @Inject constructor(
    private val router: Router,
    private val usersRepository: GithubUsersRepository,
    private val appScreens: AppScreens,
    private val usersScopeContainer: UsersScopeContainer,
) : MvpPresenter<UsersView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    fun onUserClicked(githubUserModel: GithubUserModel) {
        router.navigateTo(appScreens.userDetailsScreen(githubUserModel))
    }

    override fun onDestroy() {
        usersScopeContainer.destroyUsersSubcomponent()
        super.onDestroy()
    }

    private fun loadData() {
        usersRepository.getUsers()
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