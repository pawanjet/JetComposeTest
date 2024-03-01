package com.example.jetcomposetest.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetcomposetest.ui_layer.MovieListScreen
import com.example.jetcomposetest.ui_layer.deatils.MovieDetailsScreen

@Composable
fun MovieNavigation(navHostController: NavHostController){
    
    NavHost(navController = navHostController, startDestination = MovieNavigationItem.movieList.route){

        composable(MovieNavigationItem.movieList.route){
            MovieListScreen(navHostController)
        }

        composable(MovieNavigationItem.movieDetails.route+"/{id}"){
            val movieId = it.arguments?.getString("id")

            Log.e("MovieNavigation", "MovieNavigation movieId: $movieId")

            MovieDetailsScreen()
        }


    }
}