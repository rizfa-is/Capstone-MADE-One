package com.issog.capstonemadeone.core.data

import com.issog.capstonemadeone.core.data.source.local.LocalDataSource
import com.issog.capstonemadeone.core.data.source.remote.RemoteDataSource
import com.issog.capstonemadeone.core.data.source.remote.network.ApiResponse
import com.issog.capstonemadeone.core.data.source.remote.response.MovieResponse
import com.issog.capstonemadeone.core.data.source.remote.response.TvShowResponse
import com.issog.capstonemadeone.core.domain.model.Movie
import com.issog.capstonemadeone.core.domain.repository.IMovieRepository
import com.issog.capstonemadeone.core.utils.AppExecutors
import com.issog.capstonemadeone.core.utils.DataMapper.mapDomainToEntities
import com.issog.capstonemadeone.core.utils.DataMapper.mapEntitiesToDomain
import com.issog.capstonemadeone.core.utils.DataMapper.mapMovieResponseToEntities
import com.issog.capstonemadeone.core.utils.DataMapper.mapTvShowResponseToEntities
import com.issog.capstonemadeone.core.utils.isNull
import com.issog.capstonemadeone.core.utils.orDefault
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    val localDataSource: LocalDataSource,
    val remoteDataSource: RemoteDataSource,
    val appExecutors: AppExecutors
): IMovieRepository {
    override fun getMovies(): Flow<Resources<List<Movie>>> {
        return object : NetworkBoundResources<List<Movie>, List<MovieResponse>>() {
            override fun loadLocalDataSource(): Flow<List<Movie>> {
                return localDataSource.getMovies().map {
                    it.mapEntitiesToDomain()
                }
            }

            override suspend fun createApiCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getMovies()
            }

            override suspend fun saveApiResult(data: List<MovieResponse>) {
                localDataSource.addMovies(data.mapMovieResponseToEntities())
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data.isNull() || data?.none { !it.isTvShows }.orDefault()
            }

        }.asFlow()
    }

    override fun getTvShows(): Flow<Resources<List<Movie>>> {
        return object : NetworkBoundResources<List<Movie>, List<TvShowResponse>>() {
            override fun loadLocalDataSource(): Flow<List<Movie>> {
                return localDataSource.getTvShows().map {
                    it.mapEntitiesToDomain()
                }
            }

            override suspend fun createApiCall(): Flow<ApiResponse<List<TvShowResponse>>> {
                return remoteDataSource.getTvShows()
            }

            override suspend fun saveApiResult(data: List<TvShowResponse>) {
                localDataSource.addMovies(data.mapTvShowResponseToEntities())
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data.isNull() || data?.none { it.isTvShows }.orDefault()
            }

        }.asFlow()
    }

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovies().map {
            it.mapEntitiesToDomain()
        }
    }

    override fun getFavoriteTvShows(): Flow<List<Movie>> {
        return localDataSource.getFavoriteTvShows().map {
            it.mapEntitiesToDomain()
        }
    }

    override fun updateFavoriteMovie(movie: Movie, isFavorite: Boolean) {
        movie.favorite = isFavorite
        val favorite = movie.mapDomainToEntities()
        appExecutors.diskIO().execute { localDataSource.updateFavoriteMovie(favorite) }
    }
}