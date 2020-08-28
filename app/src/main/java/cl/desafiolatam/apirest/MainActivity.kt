package cl.desafiolatam.apirest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import cl.desafiolatam.apirest.model.base.EntityPhoto
import cl.desafiolatam.apirest.model.pojo.Photo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var adapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = PhotoAdapter(mutableListOf())
        photoRecyclerView.adapter = adapter
        val model: ViewModelPhoto by viewModels()

           model.listaPhoto.observe(this, Observer {

               Log.d("MAIN", "EN EL MAIN $it")
               var photolist = ArrayList<Photo>()
               var listaPhoto = it.map {
                   photolist.add(Photo(1, it.id, it.title, it.thumbnailUrl))
               }
               Log.d("MAIN","antes del adapter ${photolist.size}" )
                adapter.updateItem(photolist)
           })
        }
}