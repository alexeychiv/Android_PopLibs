package gb.android.android_poplibs.navigation

import android.content.Intent
import androidx.core.os.bundleOf
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import gb.android.android_poplibs.model.GithubRepoModel
import gb.android.android_poplibs.model.GithubUserModel
import gb.android.demos.okhttp.OkHTTPActivity
import gb.android.demos.pngtojpgconverter.PngToJpgConverterActivity
import gb.android.demos.rxjavademo.RxJavaDemoActivity
import gb.android.android_poplibs.ui.imageloading.GlideImageLoader
import gb.android.android_poplibs.ui.repodetails.RepoDetailsFragment
import gb.android.android_poplibs.ui.userdetails.UserDetailsFragment
import gb.android.android_poplibs.ui.users.UsersFragment

object AppScreens {

    fun usersScreen() = FragmentScreen {
        UsersFragment()
    }

    fun userDetailsScreen(githubUserModel: GithubUserModel) = FragmentScreen {
        UserDetailsFragment(GlideImageLoader()).apply {
            arguments = bundleOf(
                "githubUserModel" to githubUserModel
            )
        }
    }

    fun repoDetailsScreen(githubRepoModel: GithubRepoModel) = FragmentScreen {
        RepoDetailsFragment().apply {
            arguments = bundleOf(
                "githubRepoModel" to githubRepoModel
            )
        }
    }

    fun rxJavaDemoScreen() = ActivityScreen {
        Intent(it, RxJavaDemoActivity::class.java)
    }

    fun PngToJpgConverterScreen() = ActivityScreen {
        Intent(it, PngToJpgConverterActivity::class.java)
    }

    fun OkHTTPScreen() = ActivityScreen {
        Intent(it, OkHTTPActivity::class.java)
    }

}