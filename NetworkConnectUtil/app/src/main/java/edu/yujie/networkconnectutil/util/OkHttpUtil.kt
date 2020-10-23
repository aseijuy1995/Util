@file:JvmName("OkHttpUtil")

package edu.yujie.networkconnectutil.util

import android.content.Context
import edu.yujie.networkconnectutil.BuildConfig
import edu.yujie.networkconnectutil.pattern.SingletonProperty
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @author YuJie on 2020/7/9
 * @describe 封裝OkHttp
 * @constructor OkHttpManager.get(arg:A)
 * @param context 上下文
 */
class OkHttpUtil private constructor(context: Context) {
    private val TAG = javaClass.simpleName

    val client: OkHttpClient

    companion object : SingletonProperty<OkHttpUtil, Context>(::OkHttpUtil)

    init {
        //Cache
        val fileCache = File(context.cacheDir, "${context.packageName}.$TAG.cache")
        val cacheSize: Long = 10L * 1024L * 1024L
        val cache = Cache(fileCache, cacheSize)
        //Logger
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.BASIC
        )
        client =
            OkHttpClient.Builder().apply {
                cache(cache)
                addInterceptor(httpLoggingInterceptor)
                connectTimeout(10, TimeUnit.SECONDS)//連線超時
                readTimeout(10, TimeUnit.SECONDS)//讀取超時
                writeTimeout(10, TimeUnit.SECONDS)//寫入超時
                callTimeout(10, TimeUnit.SECONDS)
            }.build()
    }

    /**
     * Call同步
     *
     * @return 響應同步數據or Null
     */
    private fun Call.sync(): String? =
        try {
            val response = this.execute()
            if (response.isSuccessful) response.body?.string() else null
        } catch (e: IOException) {
            println("${TAG}:${e.message}")
            e.printStackTrace()
            null
        }

    /**
     * Call異步
     *
     * @param callback 異步回調處理
     */
    private fun Call.async(callback: Callback) = this.enqueue(callback)

    /**
     * Get同步
     *
     * @param url 連線路徑
     * @return 響應同步數據or Null
     */
    fun getSync(url: String): String? {
        val request = Request.Builder().get().url(url).build()
        return client.newCall(request).sync()
    }

    /**
     * Get異步
     *
     * @param url 連線路徑
     * @param callback 異步回調處理
     */
    fun getAsync(url: String, callback: Callback) {
        val request = Request.Builder().get().url(url).build()
        client.newCall(request).async(callback)
    }

    /**
     * Post同步:JSON
     *
     * @param url 連線路徑
     * @param json 請求參數:json格式
     * @return 響應同步數據or Null
     */
    fun postSyncJSON(url: String, json: String): String? {
        val requestBody = RequestBody.create("application/json;charset=utf-8".toMediaType(), json)
        val request = Request.Builder().post(requestBody).url(url).build()
        return client.newCall(request).sync()
    }

    /**
     * Post同步:Map<String, String>
     *
     * @param url 連線路徑
     * @param params 請求參數:key-value
     * @return 響應同步數據or Null
     */
    fun postSyncForm(url: String, params: Map<String, String>?): String? {
        val requestBody = buildParams(params)
        val request = Request.Builder().post(requestBody).url(url).build()
        return client.newCall(request).sync()
    }

    /**
     * Post異步:JSON
     *
     * @param url 連線路徑
     * @param json 請求參數:json格式
     * @param callback 異步回調處理
     */
    fun postAsyncJSON(url: String, json: String, callback: Callback) {
        val requestBody = RequestBody.create("application/json; charset=utf-8".toMediaType(), json)
        val request = Request.Builder().url(url).post(requestBody).build()
        client.newCall(request).async(callback)
    }

    /**
     * Post異步:Map<String, String>
     *
     * @param url 連線路徑
     * @param params 請求參數:key-value
     * @param callback 異步回調處理
     */
    fun postAsyncForm(url: String, params: Map<String, String>?, callback: Callback) {
        val requestBody = buildParams(params)
        val request = Request.Builder().url(url).post(requestBody).build()
        client.newCall(request).async(callback)
    }

    /**
     * 轉換器 map to RequestBody
     *
     * @param params 請求參數:key-value
     * @return RequestBody json數據提交
     */
    private fun buildParams(params: Map<String, String>?): RequestBody {
        val map = params ?: HashMap()
        val builder = FormBody.Builder()
        for (entry in map.entries) {
            builder.add(entry.key, entry.value)
        }
        return builder.build()
    }

    /**
     * 取消指定Call請求
     */
    fun Call.cancel() {
        //準備運行的異步
        for (dispatcherCal1 in client.dispatcher.queuedCalls()) {
            if (request().tag()!! == request().tag()) cancel()
        }
        //正在運行的任務
        for (dispatcherCal1 in client.dispatcher.runningCalls()) {
            if (request().tag()!! == request().tag()) cancel()
        }
    }

    /**
     * 取消全部請求
     */
    fun cancelAll() = client.dispatcher.cancelAll()
}