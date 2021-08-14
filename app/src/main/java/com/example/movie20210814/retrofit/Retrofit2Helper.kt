package com.example.movie20210814.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit2Helper {
    private const val BASE_URL = "https://yts.mx/api/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val movieService: MovieService = retrofit.create(MovieService::class.java)
}