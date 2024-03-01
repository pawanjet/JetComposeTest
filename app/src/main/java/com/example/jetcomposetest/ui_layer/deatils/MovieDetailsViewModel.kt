package com.example.jetcomposetest.ui_layer.deatils

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetcomposetest.common.Resource
import com.example.jetcomposetest.data.MovieRepository
import com.example.jetcomposetest.model.MovieDetailsResponse
import com.example.jetcomposetest.ui_layer.MovieStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val movieRepository: MovieRepository, savedStateHandle: SavedStateHandle): ViewModel() {

    val movieDetails = mutableStateOf(MovieDetailsStateHolder())

    init {

        movieDetails.value = MovieDetailsStateHolder(isLoading = true)

        viewModelScope.launch {

            savedStateHandle.getStateFlow("id", "0").collectLatest{

                getMovieDetails(it)
            }
        }
    }

    fun getMovieDetails(id: String) = viewModelScope.launch(Dispatchers.IO){

        val movieDetailsResult = movieRepository.getMovieDetails(id)

        when(movieDetailsResult){

            is Resource.Success -> {

                movieDetails.value = MovieDetailsStateHolder(movieDetails = movieDetailsResult.data)
            }
            is Resource.Error ->{
                movieDetails.value = MovieDetailsStateHolder(error = movieDetailsResult.message.toString())
            }
            else->{

            }
        }
    }
}