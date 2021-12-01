package gb.android.android_poplibs.ui.users

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd


interface UsersView : MvpView {

    @AddToEnd
    fun updateList()

}