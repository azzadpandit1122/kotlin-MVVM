package com.demo.retrofithttpmethods

import com.example.myapplication.mdel.MovieResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiRequest {

    // https://www.omdbapi.com/?apikey=b9bd48a6&s=Marvel&type=movie
    @GET("?apikey=b9bd48a6&s=Marvel&type=movie")
    fun getMovieList(): Call<MovieResponse>

}