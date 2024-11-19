package com.issog.capstonemadeone.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.issog.capstonemadeone.core.data.source.local.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}