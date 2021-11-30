package gb.android.android_poplibs.screens

import androidx.core.os.bundleOf
import com.github.terrakok.cicerone.androidx.FragmentScreen
import gb.android.android_poplibs.model.GithubUserModel
import gb.android.android_poplibs.ui.user.UserFragment
import gb.android.android_poplibs.ui.users.UsersFragment

object AppScreens {

    fun usersScreen() = FragmentScreen {
        UsersFragment()
    }

    fun userScreen(githubUserData: GithubUserModel) = FragmentScreen {
        UserFragment().apply {
            arguments = bundleOf(
                "githubUserData" to githubUserData
            )
        }
    }

}