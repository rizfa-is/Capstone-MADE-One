package com.issog.capstonemadeone.core.data.source.local

import com.issog.capstonemadeone.core.data.source.local.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

interface ILocalDataSource {
    fun getMovies(): Flow<List<MovieEntity>>
    fun getTvShows(): Flow<List<MovieEntity>>
    fun getFavoriteMovies(): Flow<List<MovieEntity>>
    fun getFavoriteTvShows(): Flow<List<MovieEntity>>
    suspend fun addMovies(movies: List<MovieEntity>)
    fun updateFavoriteMovie(movie: MovieEntity)
}