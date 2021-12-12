package gb.android.android_poplibs.ui.imageloading

interface ImageLoader<T> {

    fun loadInto(url: String, container: T)

}