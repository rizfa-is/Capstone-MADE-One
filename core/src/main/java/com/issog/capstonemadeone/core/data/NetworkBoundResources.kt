package com.issog.capstonemadeone.core.data

import com.issog.capstonemadeone.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResources<ResultType, RequestType> {

    private val result: Flow<Resources<ResultType>> = flow {
        emit(Resources.Loading())
        val localDataSource = loadLocalDataSource().first()
        if (shouldFetch(localDataSource)) {
            when(val response = createApiCall().first()) {
                is ApiResponse.NetworkError -> emit(Resources.NetworkError())
                is ApiResponse.Loading -> emit(Resources.Loading())
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Resources.Error(response.code, response.message))
                }
                is ApiResponse.Success -> {
                    saveApiResult(response.data)
                    emitAll(loadLocalDataSource().map {
                        Resources.Success(it)
                    })
                }
            }
        } else {
            emitAll(
                loadLocalDataSource().map {
                    Resources.Success(it)
                }
            )
        }
    }

    protected abstract fun loadLocalDataSource(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createApiCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveApiResult(data: RequestType)

    protected open fun onFetchFailed() {}

    fun asFlow(): Flow<Resources<ResultType>> = result
}