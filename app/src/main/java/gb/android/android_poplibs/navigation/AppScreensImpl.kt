package gb.android.android_poplibs.navigation

import android.content.Intent
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import gb.android.android_poplibs.model.GithubRepoModel
import gb.android.android_poplibs.model.GithubUserModel
import gb.android.android_poplibs.ui.repodetails.RepoDetailsFragment
import gb.android.android_poplibs.ui.userdetails.UserDetailsFragment
import gb.android.android_poplibs.ui.users.UsersFragment
import gb.android.demos.okhttp.OkHTTPActivity
import gb.android.demos.pngtojpgconverter.PngToJpgConverterActivity
import gb.android.demos.rxjavademo.RxJavaDemoActivity

class AppScreensImpl : AppScreens {

    override fun usersScreen() = FragmentScreen {
        UsersFragment()
    }

    override fun userDetailsScreen(githubUserModel: GithubUserModel) = FragmentScreen {
        UserDetailsFragment.newInstance(githubUserModel)
    }

    override fun repoDetailsScreen(githubRepoModel: GithubRepoModel) = FragmentScreen {
        RepoDetailsFragment.newInstance(githubRepoModel)
    }

    override fun rxJavaDemoScreen() = ActivityScreen {
        Intent(it, RxJavaDemoActivity::class.java)
    }

    override fun PngToJpgConverterScreen() = ActivityScreen {
        Intent(it, PngToJpgConverterActivity::class.java)
    }

    override fun OkHTTPScreen() = ActivityScreen {
        Intent(it, OkHTTPActivity::class.java)
    }

}