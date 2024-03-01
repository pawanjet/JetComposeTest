package com.example.jetcomposetest.ui_layer

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetcomposetest.common.Resource
import com.example.jetcomposetest.data.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository): ViewModel() {

    val movieList = mutableStateOf(MovieStateHolder())

    init {
        movieList.value = MovieStateHolder(isLoading = true)
        getMovieList()
    }
    fun getMovieList() = viewModelScope.launch (Dispatchers.IO){

        val movieListResult = movieRepository.getMovieList()

        when(movieListResult){

            is Resource.Success ->{
                movieList.value = MovieStateHolder(movieList = movieListResult.data)
            }

            is Resource.Error ->{
                movieList.value = MovieStateHolder(error = movieListResult.message.toString())
            }
            else->{

            }
        }
    }
}