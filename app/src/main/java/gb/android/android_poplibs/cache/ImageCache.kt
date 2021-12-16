package gb.android.android_poplibs.cache

import android.graphics.Bitmap
import gb.android.android_poplibs.model.GithubRepoModel
import gb.android.android_poplibs.model.GithubUserModel

interface ImageCache {

    fun getImage(url: String): Bitmap

    fun cacheImage(url: String, bitmap: Bitmap)

}