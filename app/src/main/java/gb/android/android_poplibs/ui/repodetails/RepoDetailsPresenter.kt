package gb.android.android_poplibs.ui.repodetails

import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import gb.android.android_poplibs.di.scope.containers.RepoDetailsScopeContainer
import gb.android.android_poplibs.model.GithubRepoModel
import moxy.MvpPresenter

class RepoDetailsPresenter @AssistedInject constructor(
    private val router: Router,
    @Assisted private val githubRepoModel: GithubRepoModel,
    private val repoDetailsScopeContainer: RepoDetailsScopeContainer,
) : MvpPresenter<RepoDetailsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.updateRepo(githubRepoModel)
    }

    override fun onDestroy() {
        repoDetailsScopeContainer.destroyRepoDetailsSubcomponent()
        super.onDestroy()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}