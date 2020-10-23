package edu.yujie.networkconnectutil

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MainViewModel(private val repo: Repository) : ViewModel() {

    val appResult: LiveData<AppResult> = repo.postAppResult()

}