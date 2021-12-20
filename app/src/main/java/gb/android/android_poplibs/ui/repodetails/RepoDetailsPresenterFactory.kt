package gb.android.android_poplibs.ui.repodetails

import dagger.assisted.AssistedFactory
import gb.android.android_poplibs.model.GithubRepoModel


@AssistedFactory
interface RepoDetailsPresenterFactory {

    fun presenter(githubRepoModel: GithubRepoModel): RepoDetailsPresenter

}