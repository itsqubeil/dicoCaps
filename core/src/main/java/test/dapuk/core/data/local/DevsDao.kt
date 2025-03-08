package test.dapuk.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DevsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(devs: DevsEntity)

    @Query("SELECT * FROM favorite_devs")
    fun getAllFavorites(): Flow<List<DevsEntity>>

    @Query("SELECT * FROM favorite_devs WHERE id = :devId")
    suspend fun getFavoriteById(devId: Int): DevsEntity?

    @Query("DELETE FROM favorite_devs WHERE id = :devId")
    suspend fun deleteFavoriteById(devId: Int)
}


