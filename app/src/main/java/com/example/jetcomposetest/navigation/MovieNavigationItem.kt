package com.example.jetcomposetest.navigation

sealed class MovieNavigationItem(val route: String){

    object movieList:  MovieNavigationItem("movie_list")
    object movieDetails:  MovieNavigationItem("movie_details")
}
