package test.dapuk.core.domain.usecase

import test.dapuk.core.domain.model.Devs
import test.dapuk.core.domain.model.DevsDetail
import test.dapuk.core.domain.repository.IDevsRepository

class GetDevsUseCase(private val repository: IDevsRepository) {
    suspend fun execute(apiKey: String): List<Devs>? {
        return repository.fetchDevsApi(apiKey)
    }
}

class GetDevsDetailUseCase(private val repository: IDevsRepository) {
    suspend fun execute(id: String, apiKey: String): DevsDetail? {
        return repository.fetchDevsDetail(id, apiKey)
    }
}

class FavoriteUseCase(private val repository: IDevsRepository) {
    suspend fun isFavorite(devId: Int) = repository.isFavorite(devId)
    suspend fun addFavorite(devs: Devs) = repository.addFavorite(devs)
    fun getAllFavorite() = repository.getAllFavorite()
    suspend fun removeFavoriteById(devId: Int) = repository.removeFavoriteById(devId)
}
