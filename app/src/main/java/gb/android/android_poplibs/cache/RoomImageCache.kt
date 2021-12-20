package gb.android.android_poplibs.cache

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import gb.android.android_poplibs.R
import gb.android.android_poplibs.cache.db.AppDatabase
import java.io.FileOutputStream
import java.io.IOException


class RoomImageCache(
    private val db: AppDatabase,
    private val context: Context,
) : ImageCache {

    override fun getImage(url: String): Bitmap {
        val path = db.imageDao.getPath(url)

        if (path == null) {
            return BitmapFactory.decodeResource(context.resources, R.drawable.ic_launcher_background)
        } else {
            val options = BitmapFactory.Options()
            options.inPreferredConfig = Bitmap.Config.RGB_565
            return BitmapFactory.decodeFile(path, options)
        }
    }

    override fun cacheImage(url: String, bitmap: Bitmap) {
        try {
            FileOutputStream(url).use { out ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}