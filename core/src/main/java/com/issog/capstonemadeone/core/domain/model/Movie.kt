package com.issog.capstonemadeone.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var id: Int,
    var title: String,
    var originalTitle: String,
    var originalName: String,
    var overview: String,
    var releaseDate: String,
    var voteAverage: Double,
    var voteCount: Int,
    var popularity: Double,
    var posterPath: String,
    var favorite: Boolean,
    var isTvShows: Boolean
): Parcelable
