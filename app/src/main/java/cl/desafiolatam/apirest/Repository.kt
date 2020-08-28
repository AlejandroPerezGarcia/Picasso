package cl.desafiolatam.apirest

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.desafiolatam.apirest.model.base.DataBasePhoto
import cl.desafiolatam.apirest.model.base.EntityPhoto

import cl.desafiolatam.apirest.model.pojo.Photo
import cl.desafiolatam.apirest.model.pojo.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class Repository(context: Context) {

    private var photolist = ArrayList<EntityPhoto>()
  //  private var data = MutableLiveData<List<Photo>>()
    private var instanceDataBase = DataBasePhoto.getDatabase(context).photoDao()

    val allPhoto : LiveData<List<EntityPhoto>> = instanceDataBase.getAllPhoto()

    fun loadPhotoData() {

        val service = RetrofitClient.retrofitInstance()
        val call = service.getAllPhotos()
        call.enqueue(object : Callback<ArrayList<Photo>> {

            override fun onResponse(
                call: Call<ArrayList<Photo>>,
                response: Response<ArrayList<Photo>>
            ) {
                save(response.body()!!)
            }
            override fun onFailure(call: Call<ArrayList<Photo>>, t: Throwable) {

                Log.d("MAIN", "Error : " + t)
            }
        })

    }
   fun save (resive : ArrayList<Photo>){
       var listaPhoto = resive.map {
            photolist.add(EntityPhoto(it.id,it.title, it.thumbnailUrl))

        }
      CoroutineScope(Dispatchers.IO).launch {
          Log.d("repository","lista ${photolist.size}")
          for (photo in photolist){
              instanceDataBase.insertPhoto(photo)
          }


      }
    }

}