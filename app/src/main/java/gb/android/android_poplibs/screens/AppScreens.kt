package gb.android.android_poplibs.screens

import android.content.Intent
import androidx.core.os.bundleOf
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import gb.android.android_poplibs.model.GithubUserModel
import gb.android.android_poplibs.pngtojpgconverter.PngToJpgConverterActivity
import gb.android.android_poplibs.rxjavademo.RxJavaDemoActivity
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

    fun rxJavaDemoScreen() = ActivityScreen {
        Intent(it, RxJavaDemoActivity::class.java)
    }
    fun PngToJpgConverterScreen() = ActivityScreen {
        Intent(it, PngToJpgConverterActivity::class.java)
    }

}