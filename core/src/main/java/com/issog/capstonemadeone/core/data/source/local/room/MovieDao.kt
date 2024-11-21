package com.issog.capstonemadeone.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.issog.capstonemadeone.core.data.source.local.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table WHERE isTvShow = 0")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie_table WHERE isTvShow = 1")
    fun getTvShows(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie_table WHERE isTvShow = 0 AND favorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie_table WHERE isTvShow = 1 AND favorite = 1")
    fun getFavoriteTvShows(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: List<MovieEntity>)

    @Update()
    fun updateFavoriteMovie(movieEntity: MovieEntity)
}