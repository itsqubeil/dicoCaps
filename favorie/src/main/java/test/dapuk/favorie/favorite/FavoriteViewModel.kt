package test.dapuk.favorie.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import test.dapuk.core.data.local.DevsEntity
import test.dapuk.core.data.repository.DevsRepository
import test.dapuk.core.domain.model.Devs
import test.dapuk.core.domain.usecase.FavoriteUseCase

class FavoriteViewModel(private val favoriteUseCase: FavoriteUseCase) : ViewModel() {
    private val _favoriteDevs = MutableStateFlow<List<Devs>>(emptyList())
    val favoriteDevs: LiveData<List<Devs>> = _favoriteDevs.asLiveData()
//    val favoriteDevs = favoriteUseCase.getAllFavorite().asLiveData()


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isErr = MutableLiveData<Boolean>()
    val isErr: LiveData<Boolean> = _isErr

    init {
        fetchFavorite()
    }

    fun fetchFavorite() {
        viewModelScope.launch {
            _isLoading.value = true
            favoriteUseCase.getAllFavorite().collect { favorites ->
                _favoriteDevs.value = favorites
                _isLoading.value = false
                _isErr.value = favorites.isEmpty()
            }
        }
    }
}
