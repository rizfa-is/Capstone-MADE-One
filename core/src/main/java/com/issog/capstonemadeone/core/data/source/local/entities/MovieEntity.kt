package com.issog.capstonemadeone.core.data.source.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo("title")
    var title: String,
    @ColumnInfo("overview")
    var overview: String,
    @ColumnInfo("release_date")
    var popularity: Double,
    @ColumnInfo("vote_average")
    var voteAverage: Double,
    @ColumnInfo("poster_path")
    var posterPath: String,
    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false,
    @ColumnInfo(name = "isTvShow")
    var isTvShow: Boolean = false
)