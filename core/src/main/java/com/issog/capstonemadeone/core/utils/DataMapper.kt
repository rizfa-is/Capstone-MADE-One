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
                it.overview,
                it.voteAverage,
                it.popularity,
                it.posterPath,
                it.favorite,
                it.isTvShow
            )
        }.reversed()
    }

    fun List<MovieResponse>.mapMovieResponseToEntities(): List<MovieEntity> {
        return filter { !it.adult.orDefault() }.map {
            MovieEntity(
                it.id.orDefault(),
                it.title.orEmpty(),
                it.overview.orEmpty(),
                it.popularity.orDefault(),
                it.voteAverage.orDefault(),
                it.posterPath.orEmpty()
            )
        }
    }

    fun List<TvShowResponse>.mapTvShowResponseToEntities(): List<MovieEntity> {
        return filter { !it.adult.orDefault() }.map {
            MovieEntity(
                it.id.orDefault(),
                it.name.orEmpty(),
                it.overview.orEmpty(),
                it.popularity.orDefault(),
                it.voteAverage.orDefault(),
                it.posterPath.orEmpty(),
                isTvShow = true
            )
        }
    }

    fun Movie.mapDomainToEntities(): MovieEntity {
        return MovieEntity(
            id,
            title,
            overview,
            popularity,
            voteAverage,
            posterPath,
            favorite,
            isTvShows
        )
    }
}