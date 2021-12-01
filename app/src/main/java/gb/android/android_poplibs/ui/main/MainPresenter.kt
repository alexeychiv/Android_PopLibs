package gb.android.android_poplibs.ui.main

import com.github.terrakok.cicerone.Router
import gb.android.android_poplibs.screens.AppScreens
import moxy.MvpPresenter


class MainPresenter(
    private val router: Router,
    //TODO screens list
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.replaceScreen(AppScreens.usersScreen())
    }

    fun backPressed() {
        router.exit()
    }

}