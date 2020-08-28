package cl.desafiolatam.apirest.model.base

import android.app.Application
import androidx.room.Room

class DataBaseInstance : Application() {

    companion object {
        lateinit var database: DataBasePhoto
    }
    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this,
            DataBasePhoto::class.java, "photo_database")
            .build()
    }

}