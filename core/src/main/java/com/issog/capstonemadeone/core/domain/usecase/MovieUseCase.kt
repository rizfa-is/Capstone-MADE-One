package com.issog.capstonemadeone.core.domain.usecase

import com.issog.capstonemadeone.core.data.Resources
import com.issog.capstonemadeone.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getMovies(): Flow<Resources<List<Movie>>>
    fun getTvShows(): Flow<Resources<List<Movie>>>
    fun getFavoriteMovies(): Flow<List<Movie>>
    fun getFavoriteTvShows(): Flow<List<Movie>>
    fun updateFavoriteMovie(movie: Movie, isFavorite: Boolean)
}