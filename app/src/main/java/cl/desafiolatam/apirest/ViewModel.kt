package cl.desafiolatam.apirest

import android.app.Application
import androidx.lifecycle.AndroidViewModel


class ViewModelPhoto(application: Application) : AndroidViewModel(application) {

    private var repository = Repository(application)
    val photo = repository.loadPhotoData()
    val listaPhoto = repository.allPhoto
}