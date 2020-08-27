package cl.desafiolatam.apirest.model.pojo

import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("/photos")
    fun getAllPhotos() : Call<ArrayList<Photo>>
}