package com.issog.capstonemadeone.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class BaseApiResponse<T>(
    @SerializedName("results")
    val results: List<T>
)