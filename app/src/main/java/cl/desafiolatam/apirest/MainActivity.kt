package cl.desafiolatam.apirest


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatam.apirest.model.pojo.Photo
import cl.desafiolatam.apirest.model.pojo.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private  var photolist = ArrayList<Photo>()
    private lateinit var viewAdapter: RecyclerView.Adapter<*>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewAdapter = PhotoAdapter(photolist)
        photoRecyclerView.adapter = viewAdapter
       loadPhotoData()


    }

    private fun loadPhotoData() {
        val service = RetrofitClient.retrofitInstance()
        val call = service.getAllPhotos()
        call.enqueue(object :Callback<ArrayList<Photo>>{

            override fun onResponse(
                call : Call< ArrayList < Photo>>,
                response: Response < ArrayList <Photo>>
            ){
                response.body()?.map{
                    Log.d("MAIN", "${it.title} - ${it.thumbnailUrl}")
                    photolist.add(it)
                }
                viewAdapter.notifyDataSetChanged()

            }
            override fun onFailure(call: Call < ArrayList <Photo>>, t: Throwable){

                Log.d("MAIN", "Error : " + t)
                Toast.makeText(
                    applicationContext,
                "Error: no pudimos recuperar las fotos desde la api",
                Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}