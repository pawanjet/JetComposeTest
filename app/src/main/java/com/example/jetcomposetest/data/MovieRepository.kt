package com.example.jetcomposetest.data

import com.example.jetcomposetest.common.Resource
import com.example.jetcomposetest.model.MovieDetailsResponse
import com.example.jetcomposetest.model.MovieListResponse

class MovieRepository(private val movieDataSource: MovieDataSource) {

    suspend fun getMovieList(): Resource<List<MovieListResponse.Movie>>{

        return try{
            Resource.Success(data = movieDataSource.getMovieList().results)
        }catch (e: Exception){
            Resource.Error(message = e.message.toString())
        }
    }

    suspend fun getMovieDetails(id: String): Resource<MovieDetailsResponse>{

        return try{
            Resource.Success(data = movieDataSource.getMovieDetails(id))
        }catch (e: Exception){
            Resource.Error(message = e.message.toString())
        }
    }
}