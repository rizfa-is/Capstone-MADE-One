package com.issog.capstonemadeone.core.data.source.remote

import com.issog.capstonemadeone.core.data.source.remote.network.ApiResponse
import com.issog.capstonemadeone.core.data.source.remote.response.BaseApiResponse
import com.issog.capstonemadeone.core.utils.notNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okio.IOException
import retrofit2.Response

abstract class RemoteBaseApiResponse {
    fun <T> safeApiCall(apiCall: suspend () -> Response<BaseApiResponse<T>>): Flow<ApiResponse<List<T>>> {
        return flow {
            try {
                val response = apiCall()
                if (response.isSuccessful && response.body().notNull()) {
                    emit(ApiResponse.Success(response.body()!!.results))
                } else {
                    emit(ApiResponse.Error(response.code(), response.message()))
                }
            } catch (e: IOException) {
                emit(ApiResponse.NetworkError)
            } catch (e: Exception) {
                emit(ApiResponse.Error(message = e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}