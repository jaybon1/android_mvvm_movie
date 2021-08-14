package com.example.movie20210814.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface MovieService {

    @GET("v2/list_movies.json")
    fun requestMovieList(): Call<Map<String,Any>>
}