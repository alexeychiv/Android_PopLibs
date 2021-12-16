package gb.android.android_poplibs.ui.imageloading

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import gb.android.android_poplibs.cache.ImageCache


class GlideImageLoader(
    private val imageCache: ImageCache
) : ImageLoader<ImageView> {

    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .asBitmap()
            .load(url)
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    //container.setImageBitmap(imageCache.getImage(url))
                    return true
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    /*resource?.let {
                        imageCache.cacheImage(url, it)
                    }*/
                    return false
                }

            })
            .into(container)
    }

}