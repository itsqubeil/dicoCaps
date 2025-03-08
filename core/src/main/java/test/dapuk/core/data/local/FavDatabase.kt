package test.dapuk.core.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DevsEntity::class], version = 1, exportSchema = false)
abstract class FavDatabase : RoomDatabase() {
    abstract fun devsDao(): DevsDao

    companion object {
        @Volatile
        private var INSTANCE: FavDatabase? = null

        fun getInstance(context: Context): FavDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavDatabase::class.java,
                    "favorite_devs"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}