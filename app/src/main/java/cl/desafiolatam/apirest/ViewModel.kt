package cl.desafiolatam.apirest

import androidx.lifecycle.ViewModel

class ViewModelPhoto : ViewModel() {


    private var repository = Repository()

    val photo = repository.loadPhotoData()
}