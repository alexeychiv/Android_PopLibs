package gb.android.android_poplibs.ui.repodetails

import com.github.terrakok.cicerone.Router
import gb.android.android_poplibs.model.GithubRepoModel
import moxy.MvpPresenter

class RepoDetailsPresenter(
    private val router: Router,
    private val githubRepoModel: GithubRepoModel,
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