package gb.android.demos.okhttp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import gb.android.android_poplibs.databinding.ActivityOkhttpBinding
import okhttp3.*
import java.io.IOException

class OkHTTPActivity : AppCompatActivity() {

    private var _binding: ActivityOkhttpBinding? = null
    private val binding: ActivityOkhttpBinding
        get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityOkhttpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGet.setOnClickListener {
            val okHttpClient = OkHttpClient.Builder().build()

            val request = Request.Builder()
                .url("https://api.github.com/users")
                .build()

            okHttpClient.newCall(request).enqueue(object: Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e("OkHTTP", "ERROR: Request failed!", e)
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.d("OkHTTP", "REQUEST RESPONSE: ${response.body?.string()}")
                }

            })
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}