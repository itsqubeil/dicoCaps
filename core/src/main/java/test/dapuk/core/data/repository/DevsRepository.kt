package test.dapuk.core.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import test.dapuk.core.data.local.DevsDao
import test.dapuk.core.data.remote.ApiService
import test.dapuk.core.data.utils.toDomain
import test.dapuk.core.data.utils.toEntity
import test.dapuk.core.domain.model.Devs
import test.dapuk.core.domain.repository.IDevsRepository

class DevsRepository(
    private val apiService: ApiService,
    private val devsDao: DevsDao
): IDevsRepository {
    override suspend fun isFavorite(devId: Int) = devsDao.getFavoriteById(devId) != null
    override suspend fun addFavorite(devs: Devs) = devsDao.insertFavorite(devs.toEntity())
//    override fun getAllFavorite(): Flow<List<Devs>> {devsDao.getAllFavorites().map { it.}
    override fun getAllFavorite(): Flow<List<Devs>> {
        return devsDao.getAllFavorites().map { list -> list.map { it.toDomain() } }
    }
    override suspend fun removeFavoriteById(devId: Int) = devsDao.deleteFavoriteById(devId)
    override suspend fun fetchDevsApi(apiKey: String) = apiService.getDevelopersList(apiKey).body()?.results?.map { it.toDomain() }
    override suspend fun fetchDevsDetail(id: String, apiKey: String) = apiService.getDevelopersDetails(id, apiKey).body()?.toDomain()
}
