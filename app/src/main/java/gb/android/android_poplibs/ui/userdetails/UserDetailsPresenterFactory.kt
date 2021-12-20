package gb.android.android_poplibs.ui.userdetails

import dagger.assisted.AssistedFactory
import gb.android.android_poplibs.model.GithubUserModel


@AssistedFactory
interface UserDetailsPresenterFactory {

    fun presenter(githubUserModel: GithubUserModel): UserDetailsPresenter

}