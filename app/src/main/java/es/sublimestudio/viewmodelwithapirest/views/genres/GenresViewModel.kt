package es.sublimestudio.viewmodelwithapirest.views.genres

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.sublimestudio.viewmodelwithapirest.models.Genres
import es.sublimestudio.viewmodelwithapirest.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GenresViewModel: ViewModel() {
    val genresList = MutableStateFlow(Genres())
    val loading = MutableStateFlow(false)

    fun getGenres() {
        loading.value = true
        viewModelScope.launch {
            val response = ApiService.api.getGenres()
            if (response.isSuccessful) {
                genresList.value = response.body() ?: Genres()
                Log.v("GenresVM", "Todo fenomenal en la petición de generos")
            } else {
                Log.v("GenresVM", "Error en la petición de generos ${response.toString()}")
            }

            loading.value = false
        }
    }

}