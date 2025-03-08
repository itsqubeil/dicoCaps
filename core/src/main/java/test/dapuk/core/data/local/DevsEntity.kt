package test.dapuk.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_devs")
data class DevsEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val image: String,
    val gamesCount: Int,
    val imageBackground: String,
    val slug: String
)

