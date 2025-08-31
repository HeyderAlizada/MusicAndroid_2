package com.example.mvvmsongagain.apiStuff

import com.example.mvvmsongagain.models.songsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface modelApi {

    @GET("/search")
    suspend fun getSearchedSong(@Query("q") name : String) : songsModel

}