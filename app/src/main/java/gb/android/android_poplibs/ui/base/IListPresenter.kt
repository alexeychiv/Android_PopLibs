package gb.android.android_poplibs.ui.base

interface IListPresenter<V : IItemView> {

    var itemClickListener: (IItemView) -> Unit

    fun getCount(): Int
    fun bindView(view: V)

}