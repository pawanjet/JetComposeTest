package com.example.jetcomposetest.di

import com.example.jetcomposetest.data.MovieDataSource
import com.example.jetcomposetest.data.MovieRepository
import com.example.jetcomposetest.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideDataSource(apiService: ApiService): MovieDataSource{
        return MovieDataSource(apiService)
    }

    @Provides
    fun provideMovieRepository(movieDataSource: MovieDataSource): MovieRepository{
        return MovieRepository(movieDataSource)
    }
}