package gb.android.android_poplibs.cache

import android.graphics.Bitmap

interface ImageCache {

    fun getImage(url: String): Bitmap

    fun cacheImage(url: String, bitmap: Bitmap)

}