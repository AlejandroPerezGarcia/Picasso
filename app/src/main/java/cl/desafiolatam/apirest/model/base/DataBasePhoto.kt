package cl.desafiolatam.apirest.model.base

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EntityPhoto::class], version = 1)
abstract class DataBasePhoto : RoomDatabase() {
    abstract fun photoDao(): DaoPhoto
    companion object {

        @Volatile
        private var INSTANCE: DataBasePhoto? = null
        fun getDatabase(context: Context): DataBasePhoto {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBasePhoto::class.java,
                    "photo_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
