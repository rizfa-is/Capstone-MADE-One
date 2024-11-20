package com.issog.capstonemadeone.core.data.source.local

import com.issog.capstonemadeone.core.data.source.local.entities.MovieEntity
import com.issog.capstonemadeone.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(val movieDao: MovieDao): ILocalDataSource {
    override fun getMovies(): Flow<List<MovieEntity>> {
        return movieDao.getMovies()
    }

    override fun getTvShows(): Flow<List<MovieEntity>> {
        return movieDao.getMovies()
    }

    override fun getFavoriteMovies(): Flow<List<MovieEntity>> {
        return movieDao.getFavoriteMovies()
    }

    override fun getFavoriteTvShows(): Flow<List<MovieEntity>> {
        return movieDao.getFavoriteTvShows()
    }

    override suspend fun addMovies(movies: List<MovieEntity>) {
        movieDao.insertMovie(movies)
    }

    override fun updateFavoriteMovie(movie: MovieEntity) {
        movieDao.updateFavoriteMovie(movie)
    }
}