package com.issog.capstonemadeone.core.data.source.remote

import com.issog.capstonemadeone.core.data.source.local.MovieNativeLibs
import com.issog.capstonemadeone.core.data.source.remote.network.ApiResponse
import com.issog.capstonemadeone.core.data.source.remote.network.ApiService
import com.issog.capstonemadeone.core.data.source.remote.response.BaseApiResponse
import com.issog.capstonemadeone.core.data.source.remote.response.MovieResponse
import com.issog.capstonemadeone.core.data.source.remote.response.TvShowResponse
import kotlinx.coroutines.flow.Flow

class RemoteDataSource(val apiService: ApiService): IRemoteDataSource, RemoteBaseApiResponse() {
    override suspend fun getMovies(): Flow<ApiResponse<BaseApiResponse<MovieResponse>>> {
        return safeApiCall {
            apiService.getMovies(url = MovieNativeLibs.pathGetMovies())
        }
    }

    override suspend fun getTvShows(): Flow<ApiResponse<BaseApiResponse<TvShowResponse>>> {
        return safeApiCall {
            apiService.getTvShows(url = MovieNativeLibs.pathGetTvShows())
        }
    }
}