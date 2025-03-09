package test.dapuk.dicodingcapstone.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import test.dapuk.dicodingcapstone.BuildConfig
import test.dapuk.core.domain.model.Devs
import test.dapuk.core.domain.usecase.GetDevsUseCase

class MainViewModel(private val getDevsUseCase: GetDevsUseCase) : ViewModel() {

    private val _devs = MutableStateFlow<List<Devs>?>(emptyList())
    val devs: LiveData<List<Devs>?> = _devs.asLiveData()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading.asLiveData()

    private val _isErr = MutableLiveData<Boolean>()
    val isErr: LiveData<Boolean> = _isErr

    private val _errMsg = MutableLiveData<String>()
    val errMsg: LiveData<String> = _errMsg

    init {
        getDevs()
    }

    fun getDevs(){
        Log.d("MainViewModel", "Fetching data from API...")
        val apiKey = BuildConfig.API_KEY
        viewModelScope.launch {
            try {
                Log.d("MainViewModel", "Try Fetching data from API...")
                _isLoading.value = true
                _isErr.value = false
                _errMsg.value = "no error"

                val devList = getDevsUseCase.execute(apiKey) ?: emptyList()
                _devs.value = devList
                _isErr.value = devList.isEmpty()
                _errMsg.value = if (devList.isEmpty()) "Failed Fetch Data" else "no error"

            } catch (e: Exception) {
                Log.e("MainViewModel", "Error Fetching data: ${e.message}", e)
                _isErr.value = true
                _errMsg.value = "No Internet"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
