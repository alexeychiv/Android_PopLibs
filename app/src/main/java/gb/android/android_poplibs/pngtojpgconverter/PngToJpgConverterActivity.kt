package gb.android.android_poplibs.pngtojpgconverter

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import gb.android.android_poplibs.databinding.ActivityPngToJpegConverterBinding

class PngToJpgConverterActivity : AppCompatActivity() {

    private var _binding: ActivityPngToJpegConverterBinding? = null
    private val binding: ActivityPngToJpegConverterBinding
        get() = _binding!!


    val openPng = registerForActivityResult(ActivityResultContracts.OpenDocument()) {
        if (it != null)
            Log.d("BLAH", it.toString())
    }

    val permission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            when {
                granted -> {
                    openPng.launch(arrayOf(""))
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
                // объясняем пользователю, почему нам необходимо данное разрешение
            } else {
                permission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}