plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.2")

    defaultConfig {
        applicationId = "edu.yujie.networkconnectutil"
        minSdkVersion(16)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.10")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.constraintlayout:constraintlayout:2.0.2")
    testImplementation("junit:junit:4.13.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    //okhttp
    implementation(Libs.Square.okHttpLib)
    implementation(Libs.Square.okHttpMockWebServerLib)
    implementation(Libs.Square.okHttpLoggingInterceptorLib)
    testImplementation(Libs.Square.okHttpMockWebServerTestingLib)
    //okio
    implementation(Libs.Square.okioLib)
    //retrofit
    implementation(Libs.Square.retrofitLib)
    implementation(Libs.Square.retrofitConverterGsonLib)
    implementation(Libs.Square.retrofitMockLib)
    //koin
    implementation(Libs.Koin.koinCoreLib)
    implementation(Libs.Koin.koinCoreExtLib)
    implementation(Libs.Koin.koinScopeLib)
    implementation(Libs.Koin.koinViewModelLib)
    implementation(Libs.Koin.koinFragmentLib)
    testImplementation(Libs.Koin.koinTestingLib)
    //lifecycle
    implementation(Libs.AndroidX.viewModelKtxLib)
    implementation(Libs.AndroidX.liveDataKtxLib)
    //coroutines
    implementation(Libs.JetBrains.coroutinesCoreLib)
    implementation(Libs.JetBrains.coroutinesAndroidLib)
    testImplementation(Libs.JetBrains.coroutinesTestingLib)
    //mockk
    testImplementation(Libs.mockkLib)

}