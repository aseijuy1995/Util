package edu.yujie.networkconnectutil.util

import android.content.Context
import edu.yujie.networkconnectutil.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

fun createCache(context: Context): Cache {
    val fileCache = File(context.cacheDir, "${context.packageName}.cache")
    val cacheSize: Long = 10L * 1024L * 1024L
    return Cache(fileCache, cacheSize)
}

fun createLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().setLevel(
        if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.BASIC
    )

fun createOkHttpClient(
    context: Context,
    cache: Cache = createCache(context),
    interceptor: HttpLoggingInterceptor = createLoggingInterceptor()
): OkHttpClient {
    return OkHttpClient.Builder().apply {
        cache(cache)
        addInterceptor(interceptor)
        connectTimeout(10, TimeUnit.SECONDS)//連線超時
        readTimeout(10, TimeUnit.SECONDS)//讀取超時
        writeTimeout(10, TimeUnit.SECONDS)//寫入超時
        callTimeout(10, TimeUnit.SECONDS)
    }.build()
}

inline fun <reified T> createRetrofit(
    client: OkHttpClient,
    baseUrl: String
): Retrofit =
    Retrofit.Builder().apply {
        baseUrl(baseUrl)
        addConverterFactory(GsonConverterFactory.create())
        client(client)
    }.build()

inline fun <reified T> createService(retrofit: Retrofit): T = retrofit.create(T::class.java)
