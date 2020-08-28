package cl.desafiolatam.apirest.model.base

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DaoPhoto {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhoto (photo: EntityPhoto)


    @Query("Select * from photos")
    fun getAllPhoto(): LiveData<List<EntityPhoto>>

  /*  @Insert
    suspend fun insertListPhotos(listPhotos: List<EntityPhoto>)
*/

}