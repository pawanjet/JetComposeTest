package com.example.jetcomposetest.ui_layer.deatils

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun MovieDetailsScreen(viewModel: MovieDetailsViewModel = hiltViewModel()) {

    val result = viewModel.movieDetails.value

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {

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

        result.movieDetails?.let {

            Column(modifier = Modifier.padding(horizontal = 12.dp)) {

                AsyncImage(model = "https://image.tmdb.org/t/p/w500/${it.poster_path}", contentDescription ="Movie poster",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .padding(horizontal = 12.dp), contentScale = ContentScale.FillBounds)


                Text(text = it.original_title, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp), style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold), color = Color.Black)

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = it.tagline, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp), style = TextStyle(fontSize = 15.sp), color = Color.Black)

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = it.overview, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp), style = TextStyle(fontSize = 12.sp), color = Color.Black)

            }
        }
    }
}