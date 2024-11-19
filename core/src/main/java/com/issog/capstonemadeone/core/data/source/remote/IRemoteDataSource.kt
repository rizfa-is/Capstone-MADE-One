package com.issog.capstonemadeone.core.data.source.remote

import com.issog.capstonemadeone.core.data.source.remote.network.ApiResponse
import com.issog.capstonemadeone.core.data.source.remote.response.BaseApiResponse
import com.issog.capstonemadeone.core.data.source.remote.response.MovieResponse
import com.issog.capstonemadeone.core.data.source.remote.response.TvShowResponse
import kotlinx.coroutines.flow.Flow

interface IRemoteDataSource {
    suspend fun getMovies(): Flow<ApiResponse<BaseApiResponse<MovieResponse>>>
    suspend fun getTvShows(): Flow<ApiResponse<BaseApiResponse<TvShowResponse>>>
}