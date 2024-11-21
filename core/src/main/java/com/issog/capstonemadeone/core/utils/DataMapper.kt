package com.issog.capstonemadeone.core.utils

import com.issog.capstonemadeone.core.data.source.local.entities.MovieEntity
import com.issog.capstonemadeone.core.data.source.remote.response.MovieResponse
import com.issog.capstonemadeone.core.data.source.remote.response.TvShowResponse
import com.issog.capstonemadeone.core.domain.model.Movie

object DataMapper {
    fun List<MovieEntity>.mapEntitiesToDomain(): List<Movie> {
        return map {
            Movie(
                it.id,
                it.title,
                it.originalTitle,
                it.originalName,
                it.overview,
                it.releaseDate,
                it.voteAverage,
                it.voteCount,
                it.popularity,
                it.posterPath,
                it.favorite,
                it.isTvShow
            )
        }
    }

    fun List<MovieResponse>.mapMovieResponseToEntities(): List<MovieEntity> {
        return map {
            MovieEntity(
                it.id,
                it.title,
                it.overview,
                it.releaseDate,
                it.popularity,
                it.voteAverage,
                it.voteCount,
                it.posterPath,
                it.originalTitle
            )
        }
    }

    fun List<TvShowResponse>.mapTvShowResponseToEntities(): List<MovieEntity> {
        return map {
            MovieEntity(
                it.id,
                it.title,
                it.overview,
                it.releaseDate,
                it.popularity,
                it.voteAverage,
                it.voteCount,
                it.posterPath,
                it.originalName,
                isTvShow = true
            )
        }
    }

    fun Movie.mapDomainToEntities(): MovieEntity {
        return MovieEntity(
            id,
            title,
            overview,
            releaseDate,
            popularity,
            voteAverage,
            voteCount,
            posterPath,
            originalTitle,
            originalName,
            favorite,
            isTvShows
        )
    }
}