package edu.yujie.networkconnectutil

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import org.koin.core.KoinComponent
import org.koin.core.inject

class Repository : KoinComponent {
    val service: APIService by inject()

    fun postAppResult(): LiveData<AppResult> = liveData {
        val appResult = service.postAppResult()
        emit(appResult)
    }
}