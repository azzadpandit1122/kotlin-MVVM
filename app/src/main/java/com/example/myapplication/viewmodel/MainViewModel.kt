package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emango.kotlinV1.repository.UserRepository
import com.example.myapplication.mdel.MovieResponse

class MainViewModel : ViewModel() {
    var userRepo: UserRepository = UserRepository()
    var movieResponse: LiveData<MovieResponse?> = MutableLiveData()

    init {
        movieResponse = userRepo.getMoviesResponseList()
    }

    fun setMoviewRequest() {
        userRepo.moviewRequest()
    }

    fun getMoviewResponse(): LiveData<MovieResponse?> {
        return movieResponse
    }

}