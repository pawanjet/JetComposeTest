package com.example.jetcomposetest.network

import com.example.jetcomposetest.model.MovieDetailsResponse
import com.example.jetcomposetest.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    // https://api.themoviedb.org/3/movie/popular?api_key=8f710e1a1c2b873dbb7e72d4249e8a8e
    @GET("3/movie/popular")
    suspend fun getMovieList(@Query("api_key") apiKey: String): MovieListResponse

    @GET("3/movie/{id}")
    suspend fun getMovieDetail(@Path("id") id: String,
                               @Query("api_key") apiKey: String): MovieDetailsResponse
}