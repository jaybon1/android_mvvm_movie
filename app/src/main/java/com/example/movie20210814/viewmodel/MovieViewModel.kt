package com.example.movie20210814.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie20210814.model.MovieDTO
import com.example.movie20210814.retrofit.Retrofit2Helper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val movieDTOListLive: MutableLiveData<List<MovieDTO>> = MutableLiveData(listOf())
    private val userDataLive : MutableLiveData<String> = MutableLiveData("")

    fun bindMovieDTOListLive(): LiveData<List<MovieDTO>> {
        return movieDTOListLive
    }

    fun bindUserDataLive() : LiveData<String>{
        return userDataLive
    }

    fun getUserData(){
        userDataLive.value = "영화리스트"
    }

    fun getList() {
        Log.d(TAG, "getList: ")
        Retrofit2Helper.movieService.requestMovieList()
            .enqueue(object : Callback<Map<String, Any>> {
                override fun onResponse(
                    call: Call<Map<String, Any>>,
                    response: Response<Map<String, Any>>
                ) {
                    if (response.isSuccessful) {
                        
                        val tempMovieList = (response.body()
                            ?.get("data") as Map<String, Any>).get("movies") as List<Map<String, Any>>

//                        Log.d(TAG, "onResponse: $tempMovieList")

                        movieDTOListLive.value = tempMovieList.map { movie: Map<String, Any> ->
                            MovieDTO(
                                id = (movie.get("id") as Double).toInt(),
                                title = movie.get("title_long") as String,
                                rating = (movie.get("rating") as Double).toFloat(),
                                coverImage = movie.get("large_cover_image") as String
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<Map<String, Any>>, t: Throwable) {
                    Log.d(TAG, "onFailure: ")
                }

            })
    }
    
    companion object{
        private const val TAG = "MovieViewModel"
    }
}