package com.example.jetcomposetest.ui_layer.deatils

import com.example.jetcomposetest.model.MovieDetailsResponse
import com.example.jetcomposetest.model.MovieListResponse

data class MovieDetailsStateHolder(val isLoading: Boolean = false,
                                   val movieDetails: MovieDetailsResponse? = null,
                                   val error: String = "")