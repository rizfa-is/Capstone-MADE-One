package com.issog.capstonemadeone.core.domain.usecase

import com.issog.capstonemadeone.core.data.Resources
import com.issog.capstonemadeone.core.domain.model.Movie
import com.issog.capstonemadeone.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieAppInteractor(val repository: IMovieRepository): MovieUseCase {
    override fun getMovies(): Flow<Resources<List<Movie>>> =
        repository.getMovies()

    override fun getTvShows(): Flow<Resources<List<Movie>>> =
        repository.getTvShows()

    override fun getFavoriteMovies(): Flow<List<Movie>> =
        repository.getFavoriteMovies()

    override fun getFavoriteTvShows(): Flow<List<Movie>> =
        repository.getFavoriteTvShows()

    override fun updateFavoriteMovie(movie: Movie, isFavorite: Boolean) =
        repository.updateFavoriteMovie(movie, isFavorite)
}