package gb.android.demos.pngtojpgconverter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import io.reactivex.rxjava3.core.Single
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit

class PngToJpgConverter(
    private val context: Context
) {

    fun convert(uri: Uri): Single<Bitmap> {
        return loadPng(uri = uri)
            .delay(3000, TimeUnit.MILLISECONDS)
            .flatMap {
                decodeImage(imageDecoderSource = it)
            }
            .delay(3000, TimeUnit.MILLISECONDS)
            .flatMap {
                convertPngAndSaveJpeg(it, "convertOutput.jpg")
            }
    }


    private fun loadPng(uri: Uri): Single<ImageDecoder.Source> {
        return Single.create {
            try {
                val source = ImageDecoder.createSource(context.contentResolver, uri)
                it.onSuccess(source)
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }


    private fun decodeImage(imageDecoderSource: ImageDecoder.Source): Single<Bitmap> {
        return Single.create {
            try {
                val bitmap = ImageDecoder.decodeBitmap(imageDecoderSource)
                it.onSuccess(bitmap)
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }

    private fun convertPngAndSaveJpeg(bitmap: Bitmap, fileName: String): Single<Bitmap> {
        return Single.create {
            val file = File(context.cacheDir, fileName)
            file.createNewFile()

            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            val byteArray = stream.toByteArray()

            val fileOutputStream = FileOutputStream(file)
            fileOutputStream.write(byteArray)
            fileOutputStream.flush()
            fileOutputStream.close()

            it.onSuccess(bitmap)
        }
    }
}