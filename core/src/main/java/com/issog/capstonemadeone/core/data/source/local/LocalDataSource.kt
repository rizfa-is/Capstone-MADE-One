package com.issog.capstonemadeone.core.data.source.local

import com.issog.capstonemadeone.core.data.source.local.entities.MovieEntity
import com.issog.capstonemadeone.core.data.source.local.room.MovieDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class LocalDataSource(val movieDao: MovieDao): ILocalDataSource {
    override fun getMovies(): Flow<List<MovieEntity>> {
        return movieDao.getMovies()
    }

    override fun getTvShows(): Flow<List<MovieEntity>> {
        return movieDao.getTvShows()
    }

    override fun getFavoriteMovies(): Flow<List<MovieEntity>> {
        return movieDao.getFavoriteMovies()
            .flowOn(Dispatchers.Default)
    }

    override fun getFavoriteTvShows(): Flow<List<MovieEntity>> {
        return movieDao.getFavoriteTvShows()
            .flowOn(Dispatchers.Default)
    }

    override suspend fun addMovies(movies: List<MovieEntity>) {
        movieDao.insertMovie(movies)
    }

    override fun updateFavoriteMovie(movie: MovieEntity) {
        movieDao.updateFavoriteMovie(movie)
    }
}