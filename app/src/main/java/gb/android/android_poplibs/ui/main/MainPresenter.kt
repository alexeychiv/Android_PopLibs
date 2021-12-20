package gb.android.android_poplibs.ui.main

import com.github.terrakok.cicerone.Router
import gb.android.android_poplibs.navigation.AppScreens
import moxy.MvpPresenter
import javax.inject.Inject


class MainPresenter @Inject constructor(
    private val router: Router,
    private val appScreens: AppScreens,
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(appScreens.usersScreen())
    }

    fun backPressed() {
        router.exit()
    }


    fun openRxJavaDemo() {
        router.navigateTo(appScreens.rxJavaDemoScreen())
    }

    fun openPngToJpegConverter() {
        router.navigateTo(appScreens.PngToJpgConverterScreen())
    }

    fun openOkHTTPDemo() {
        router.navigateTo(appScreens.OkHTTPScreen())
    }

}
