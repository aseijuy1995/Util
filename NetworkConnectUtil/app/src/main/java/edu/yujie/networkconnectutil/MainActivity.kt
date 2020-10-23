package edu.yujie.networkconnectutil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import edu.yujie.myapplication.util.getMethodName
import edu.yujie.networkconnectutil.databinding.ActivityMainBinding
import edu.yujie.networkconnectutil.util.OkHttpUtil
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            lifecycleOwner = this@MainActivity
            viewModel = this@MainActivity.viewModel
        }
//        get()
//        post()

        viewModel.appResult.observe(this) {
            println("$TAG:AppResult:$it")
        }
    }

    private fun get() {
        val urlGet = "https://jsonplaceholder.typicode.com/posts"
        OkHttpUtil.get(this).getAsync(urlGet, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("$TAG:${getMethodName()}${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                println("$TAG:${getMethodName()}, code:${response.code}")
                if (response.isSuccessful) println("$TAG, response:${response}")
            }

        })
    }

    private fun post() {
        val urlPost = "https://www.letsgoshopping.com.tw/ct/api.php"
        val map = mapOf("cmd" to "get_version_android")
        OkHttpUtil.get(this).postAsyncForm(urlPost, map, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("$TAG:${getMethodName()}${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                println("$TAG:${getMethodName()}, code:${response.code}")
                if (response.isSuccessful) println("$TAG, response:${response}")
            }

        })
    }
}