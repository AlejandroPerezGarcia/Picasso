package cl.desafiolatam.apirest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import cl.desafiolatam.apirest.model.pojo.Photo
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var photolist = ArrayList<Photo>()
    private lateinit var adapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = PhotoAdapter(photolist)
        photoRecyclerView.adapter = adapter
        val model: ViewModelPhoto by viewModels()

        model.photo.observe(this, Observer {
            adapter.updateItem(it)
        })
    }
}