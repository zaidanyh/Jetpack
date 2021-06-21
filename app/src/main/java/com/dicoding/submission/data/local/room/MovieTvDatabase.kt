package com.dicoding.submission.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.submission.data.local.entity.MoviesEntity
import com.dicoding.submission.data.local.entity.TvEntity

@Database(
    entities = [MoviesEntity::class, TvEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieTvDatabase: RoomDatabase() {
    abstract fun movieTvDao(): MovieTvDao

    companion object {
        @Volatile
        private var INSTANCE: MovieTvDatabase? = null

        fun getInstance(context: Context): MovieTvDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(context.applicationContext, MovieTvDatabase::class.java, "Movie.db").build()
            }
    }
}