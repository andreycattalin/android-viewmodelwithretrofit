package es.sublimestudio.viewmodelwithapirest.remote

import es.sublimestudio.viewmodelwithapirest.models.Genres
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language
    ): Response<Genres>

}