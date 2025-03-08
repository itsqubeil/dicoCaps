package test.dapuk.dicodingcapstone.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import test.dapuk.dicodingcapstone.BuildConfig
import test.dapuk.core.data.local.DevsEntity
import test.dapuk.core.data.repository.DevsRepository
import test.dapuk.core.data.remote.Detail
import test.dapuk.core.domain.model.Devs
import test.dapuk.core.domain.model.DevsDetail
import test.dapuk.core.domain.usecase.FavoriteUseCase
import test.dapuk.core.domain.usecase.GetDevsDetailUseCase

class DetailViewModel (
    private val getDevsDetailUseCase: GetDevsDetailUseCase,
    private val favoriteUseCase: FavoriteUseCase
): ViewModel() {

    private val _devs = MutableStateFlow<DevsDetail?>(null)
    val devs: LiveData<DevsDetail?> = _devs.asLiveData()

    private val _id = MutableLiveData<String>()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading.asLiveData()

    private val _isErr = MutableLiveData<Boolean>()
    val isErr: LiveData<Boolean> = _isErr

    private val _isFav = MutableStateFlow(false)
    val isFav: LiveData<Boolean> = _isFav.asLiveData()

    private val _viewCont = MutableLiveData<Boolean>()
    val viewCont: LiveData<Boolean> = _viewCont

    private val _errMsg = MutableLiveData<String>()
    val errMsg: LiveData<String> = _errMsg

    fun setDetailId(id: String) {
        _id.value = id
    }

    init {
        _id.observeForever { id ->
            getDevs(id)
        }
    }

    fun checkFavoriteStatus(devId: Int) {
        viewModelScope.launch {
            _isFav.value = favoriteUseCase.isFavorite(devId)
        }
    }

    fun toggleFavorite(devs: Devs) {
        viewModelScope.launch {
            if (_isFav.value == true) {
                favoriteUseCase.removeFavoriteById(devs.id)
            } else {
                favoriteUseCase.addFavorite(devs)
            }
            checkFavoriteStatus(devs.id)
        }
    }


    fun getDevs(id: String) {
        val apiKey = BuildConfig.API_KEY
        _viewCont.value = false

        viewModelScope.launch {
            try {
                _isLoading.value = true
                _isErr.value = false
                _errMsg.value = "no error"

                val devs = getDevsDetailUseCase.execute(id, apiKey)
                if (devs != null) {
                    _devs.value = devs
                    _isErr.value = false
                    _viewCont.value = true
                } else {
                    _isErr.value = true
                    _errMsg.value = "Failed Fetch Data"
                }
            } catch (e: Exception) {
                _isErr.value = true
                _errMsg.value = "No Internet"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
