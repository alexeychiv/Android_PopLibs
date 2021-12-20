package gb.android.android_poplibs.ui.repodetails

import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import gb.android.android_poplibs.domain.GithubRepoRepository
import gb.android.android_poplibs.model.GithubRepoModel
import gb.android.android_poplibs.model.GithubUserModel
import gb.android.android_poplibs.navigation.AppScreens
import moxy.MvpPresenter

class RepoDetailsPresenter @AssistedInject constructor(
    private val router: Router,
    @Assisted private val githubRepoModel: GithubRepoModel,
) : MvpPresenter<RepoDetailsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.updateRepo(githubRepoModel)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}