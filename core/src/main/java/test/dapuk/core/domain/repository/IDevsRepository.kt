package test.dapuk.core.domain.repository

import kotlinx.coroutines.flow.Flow
import test.dapuk.core.domain.model.Devs
import test.dapuk.core.domain.model.DevsDetail

interface IDevsRepository {
    suspend fun isFavorite(devId: Int): Boolean
    suspend fun addFavorite(devs: Devs)
    fun getAllFavorite(): Flow<List<Devs>>
    suspend fun removeFavoriteById(devId: Int)
    suspend fun fetchDevsApi(apiKey: String): List<Devs>?
    suspend fun fetchDevsDetail(id: String, apiKey: String): DevsDetail?
}
