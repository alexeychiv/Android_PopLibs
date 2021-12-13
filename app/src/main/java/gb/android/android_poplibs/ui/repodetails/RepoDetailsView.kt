package gb.android.android_poplibs.ui.repodetails

import gb.android.android_poplibs.model.GithubRepoModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle


interface RepoDetailsView : MvpView {

    @AddToEndSingle
    fun updateRepo(githubRepoModel: GithubRepoModel)

}