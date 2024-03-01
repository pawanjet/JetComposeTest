package com.example.jetcomposetest.data

import com.example.jetcomposetest.network.ApiService

class MovieDataSource(private val apiService: ApiService) {

    suspend fun getMovieList() = apiService.getMovieList(apiKey = "8f710e1a1c2b873dbb7e72d4249e8a8e")

    suspend fun getMovieDetails(id:String) = apiService.getMovieDetail(apiKey = "8f710e1a1c2b873dbb7e72d4249e8a8e", id = id)
}