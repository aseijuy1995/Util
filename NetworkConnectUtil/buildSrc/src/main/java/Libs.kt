object Libs {
    object Square {
        //okhttp3
        const val okHttpLib = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        const val okHttpMockWebServerLib = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"
        const val okHttpLoggingInterceptorLib = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
        const val okHttpMockWebServerTestingLib = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"

        //okio
        const val okioLib = "com.squareup.okio:okio:${Versions.okio}"

        //retrofit
        const val retrofitLib = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitConverterGsonLib = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val retrofitMockLib = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"
    }

    object Koin {
        const val koinCoreLib = "org.koin:koin-core:${Versions.koin}"
        const val koinCoreExtLib = "org.koin:koin-core-ext:${Versions.koin}"
        const val koinScopeLib = "org.koin:koin-androidx-scope:${Versions.koin}"
        const val koinViewModelLib = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
        const val koinFragmentLib = "org.koin:koin-androidx-fragment:${Versions.koin}"
        const val koinTestingLib = "org.koin:koin-test:${Versions.koin}"
    }

    object AndroidX{
        const val viewModelKtxLib = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val liveDataKtxLib = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    }

    object JetBrains{
        const val coroutinesCoreLib = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val coroutinesAndroidLib = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val coroutinesTestingLib = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }

    //mockk
    const val mockkLib = "io.mockk:mockk:${Versions.mockk}"
}