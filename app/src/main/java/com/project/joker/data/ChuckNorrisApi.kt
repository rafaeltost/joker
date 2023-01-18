package com.project.joker.data

import com.project.joker.model.Joke
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckNorrisApi {

    @GET("jokes/categories")
    fun findAllCategories(@Query("apiKey") apiKey: String = HTTPClient.API_KEY): Call<List<String>>

    @GET("jokes/random")
    fun findRandom(
        @Query("category") categoryName: String? = null,
        @Query("apiKey") apiKey: String = HTTPClient.API_KEY
    ): Call<Joke>
}