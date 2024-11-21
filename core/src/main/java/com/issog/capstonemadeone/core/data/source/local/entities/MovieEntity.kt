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
    var releaseDate: String,
    @ColumnInfo("popularity")
    var popularity: Double,
    @ColumnInfo("vote_average")
    var voteAverage: Double,
    @ColumnInfo("vote_count")
    var voteCount: Int,
    @ColumnInfo("poster_path")
    var posterPath: String,
    @ColumnInfo("original_title")
    var originalTitle: String = "",
    @ColumnInfo("original_name")
    var originalName: String = "",
    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false,
    @ColumnInfo(name = "isTvShow")
    var isTvShow: Boolean = false
)