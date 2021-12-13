package gb.android.demos.pngtojpgconverter

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import gb.android.android_poplibs.databinding.ActivityPngToJpegConverterBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class PngToJpgConverterActivity : AppCompatActivity() {

    private var _binding: ActivityPngToJpegConverterBinding? = null
    private val binding: ActivityPngToJpegConverterBinding
        get() = _binding!!

    private val converter by lazy {
        PngToJpgConverter(this)
    }

    private var convertImageDisposable: Disposable? = null

    private val convertPng =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
            uri?.let {
                showImage(it)
            }
        }

    private val permission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            when {
                granted -> {
                    convertPng.launch(arrayOf("image/*"))
                }
                !shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE) -> {
                    Log.d("BLAH", "No permission Manifest.permission.WRITE_EXTERNAL_STORAGE")
                }
                else -> {
                    Log.d("BLAH", "No permission Manifest.permission.WRITE_EXTERNAL_STORAGE")
                }
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityPngToJpegConverterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLoadPngImage.setOnClickListener {
            if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Log.d(
                    "BLAH",
                    "Converter wont work without permission to read/write in External Storage"
                )
            } else {
                permission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }

        binding.btnCancel.setOnClickListener {
            convertImageDisposable?.dispose()
            convertImageDisposable = null
            binding.btnCancel.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showImage(uri: Uri) {
        binding.btnCancel.visibility = View.VISIBLE
        convertImageDisposable = converter.convert(uri)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    binding.imgLoadedPngImage.setImageBitmap(it)
                    binding.textSuccess.visibility = View.VISIBLE
                    binding.btnCancel.visibility = View.GONE
                },
                {
                    Log.d("RxJava", "Error: Image Converter - $it")
                    binding.btnCancel.visibility = View.GONE
                }
            )
    }

}