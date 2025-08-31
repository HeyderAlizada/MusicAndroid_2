package com.example.mvvmsongagain.apiStuff

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retro {

    val retro = Retrofit.Builder().baseUrl("https://api.deezer.com").addConverterFactory(
        GsonConverterFactory.create()
    ).build()

    val api = retro.create(modelApi::class.java)

}