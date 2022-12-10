package com.emango.kotlinV1.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.demo.retrofithttpmethods.RetroInstance
import com.example.myapplication.mdel.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    private val TAG = UserRepository::class.java.simpleName
    var apiCall = RetroInstance.service

    var movieResponse: MutableLiveData<MovieResponse?> = MutableLiveData()

    fun moviewRequest() {
        apiCall.getMovieList().enqueue(object : Callback<MovieResponse> {
                override fun onResponse(call: Call<MovieResponse>,response: Response<MovieResponse>) {
                    movieResponse.postValue(response.body())
                }
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    movieResponse.postValue(null)
                }
            })
    }
    fun getMoviesResponseList() : MutableLiveData<MovieResponse?>{
        return movieResponse
    }

}
