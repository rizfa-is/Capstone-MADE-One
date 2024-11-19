package com.issog.capstonemadeone.core.data.source.remote.network

import com.issog.capstonemadeone.core.data.source.remote.response.BaseApiResponse
import com.issog.capstonemadeone.core.data.source.remote.response.MovieResponse
import com.issog.capstonemadeone.core.data.source.remote.response.TvShowResponse
import retrofit2.Response
import retrofit2.http.Url

interface ApiService {
    suspend fun getMovies(@Url url: String): Response<BaseApiResponse<MovieResponse>>
    suspend fun getTvShows(@Url url : String): Response<BaseApiResponse<TvShowResponse>>
}