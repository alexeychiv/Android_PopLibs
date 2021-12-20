package gb.android.android_poplibs.navigation

import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import gb.android.android_poplibs.model.GithubRepoModel
import gb.android.android_poplibs.model.GithubUserModel

interface AppScreens {

    fun usersScreen(): FragmentScreen

    fun userDetailsScreen(githubUserModel: GithubUserModel): FragmentScreen

    fun repoDetailsScreen(githubRepoModel: GithubRepoModel): FragmentScreen

    fun rxJavaDemoScreen(): ActivityScreen

    fun PngToJpgConverterScreen(): ActivityScreen

    fun OkHTTPScreen(): ActivityScreen

}