package com.example.jetcomposetest.ui_layer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.jetcomposetest.model.MovieListResponse
import com.example.jetcomposetest.navigation.MovieNavigationItem

@Composable
fun MovieListScreen(navController: NavController, viewModel: MovieViewModel = hiltViewModel()) {

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {

        var result = viewModel.movieList.value

        if (result.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                CircularProgressIndicator()
            }
        }

        if (result.error.isNotBlank()) {

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                Text(text = result.error)
            }
        }

        result.movieList?.let { movieList ->

            LazyColumn {

                items(movieList) {

                    MovieItem(it){
                        navController.navigate(MovieNavigationItem.movieDetails.route+"/$it")
                    }
                }
            }
        }

    }
}

@Composable
fun MovieItem(movie: MovieListResponse.Movie, onClick: (String) -> Unit) {

    AsyncImage(model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
        contentDescription = "movie poster",
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(vertical = 4.dp)
            .clickable {
                onClick.invoke(movie.id.toString())
            },
        contentScale = ContentScale.FillBounds)

}
