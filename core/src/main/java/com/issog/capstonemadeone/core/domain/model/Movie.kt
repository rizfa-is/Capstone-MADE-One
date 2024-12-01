package com.issog.capstonemadeone.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var id: Int,
    var title: String,
    var overview: String,
    var voteAverage: Double,
    var popularity: Double,
    var posterPath: String,
    var favorite: Boolean,
    var isTvShows: Boolean
): Parcelable
