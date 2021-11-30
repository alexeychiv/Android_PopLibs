package gb.android.android_poplibs.ui.base

import gb.android.android_poplibs.ui.users.UserItemView

interface IListPresenter<V : IItemView> {

    var itemClickListener: (IItemView) -> Unit

    fun getCount(): Int
    fun bindView(view: V)

}