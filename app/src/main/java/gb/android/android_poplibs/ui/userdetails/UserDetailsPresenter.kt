package gb.android.android_poplibs.ui.userdetails

import android.util.Log
import com.github.terrakok.cicerone.Router
import gb.android.android_poplibs.domain.GithubRepoRepository
import gb.android.android_poplibs.model.GithubRepoModel
import gb.android.android_poplibs.model.GithubUserModel
import gb.android.android_poplibs.navigation.AppScreens
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class UserDetailsPresenter(
    private val router: Router,
    private val githubUserModel: GithubUserModel,
    private val githubRepoRepository: GithubRepoRepository,
) : MvpPresenter<UserDetailsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.updateUser(githubUserModel)
        loadReposData()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    private fun loadReposData() {
        githubRepoRepository.getRepos(githubUserModel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                viewState.showLoading()
            }
            .subscribe(
                { repoList ->
                    viewState.updateRepoList(repoList)
                    viewState.hideLoading()
                },
                { e ->
                    Log.e("Retrofit", "ERROR: Unable to receive repo list!", e)
                    viewState.hideLoading()
                }
            )
    }

    fun onRepoClicked(githubRepoModel: GithubRepoModel) {
        router.navigateTo(AppScreens.repoDetailsScreen(githubRepoModel))
    }

}
