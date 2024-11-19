package com.issog.capstonemadeone.core.data.source.remote

import com.issog.capstonemadeone.core.data.source.remote.network.ApiResponse
import com.issog.capstonemadeone.core.utils.notNull
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.Response

abstract class RemoteBaseApiResponse {
    fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Flow<ApiResponse<T>> {
        return flow {
            emit(ApiResponse.Loading)
            try {
                val response = apiCall()
                if (response.isSuccessful && response.body().notNull()) {
                    emit(ApiResponse.Success(response.body()!!))
                } else {
                    emit(ApiResponse.Error(response.code(), response.message()))
                }
            } catch (e: IOException) {
                emit(ApiResponse.NetworkError)
            } catch (e: Exception) {
                emit(ApiResponse.Error(message = e.message.toString()))
            }
        }
    }
}