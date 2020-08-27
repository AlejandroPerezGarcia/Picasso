package cl.desafiolatam.apirest

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatam.apirest.model.pojo.Photo
import cl.desafiolatam.apirest.model.pojo.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private  var photolist = ArrayList<Photo>()

    private var data = MutableLiveData<List<Photo>>()

     fun loadPhotoData():LiveData<List<Photo>> {

        val service = RetrofitClient.retrofitInstance()
        val call = service.getAllPhotos()
        call.enqueue(object : Callback<ArrayList<Photo>> {

            override fun onResponse(
                call : Call<ArrayList<Photo>>,
                response: Response<ArrayList<Photo>>
            ){
                response.body()?.map{
                    Log.d("MAIN", "${it.title} - ${it.thumbnailUrl}")
                    photolist.add(it)
                    data.postValue(photolist)
                }


            }
            override fun onFailure(call: Call<ArrayList<Photo>>, t: Throwable){

                Log.d("MAIN", "Error : " + t)


        }

        })
        return data
    }
}