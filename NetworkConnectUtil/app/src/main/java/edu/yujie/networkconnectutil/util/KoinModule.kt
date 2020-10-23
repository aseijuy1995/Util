package edu.yujie.networkconnectutil.util

import edu.yujie.networkconnectutil.APIService
import edu.yujie.networkconnectutil.MainViewModel
import edu.yujie.networkconnectutil.Repository
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

//okHttp & retrofit
val connModule = module {
    single<OkHttpClient> { createOkHttpClient(androidContext()) }
    single<Retrofit> { createRetrofit<APIService>(get(), "https://www.letsgoshopping.com.tw/ct/api.php/") }
    single<APIService> { createService<APIService>(get()) as APIService }
}

//viewModel & repo
val dataModule = module {
    single { Repository() }
    viewModel { MainViewModel(get()) }
}