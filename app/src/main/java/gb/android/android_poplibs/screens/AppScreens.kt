package gb.android.android_poplibs.screens

import androidx.core.os.bundleOf
import com.github.terrakok.cicerone.androidx.FragmentScreen
import gb.android.android_poplibs.model.GithubUserModel
import gb.android.android_poplibs.ui.user.UserDetailsFragment
import gb.android.android_poplibs.ui.users.UsersFragment

object AppScreens {

    fun usersScreen() = FragmentScreen {
        UsersFragment()
    }

    fun userScreen(githubUserModel: GithubUserModel) = FragmentScreen {
        UserDetailsFragment().apply {
            arguments = bundleOf(
                "githubUserModel" to githubUserModel
            )
        }
    }

}