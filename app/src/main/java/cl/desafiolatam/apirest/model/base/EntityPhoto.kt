package cl.desafiolatam.apirest.model.base

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
data class EntityPhoto (@PrimaryKey val id : Int, val title: String, val thumbnailUrl : String)


