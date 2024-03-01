package com.example.jetcomposetest.ui_layer

import com.example.jetcomposetest.common.Resource
import com.example.jetcomposetest.model.MovieListResponse

data class MovieStateHolder(
    val isLoading: Boolean = false,
    val movieList: List<MovieListResponse.Movie>? = null,
    val error: String = ""
)