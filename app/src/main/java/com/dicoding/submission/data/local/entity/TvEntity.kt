package com.dicoding.submission.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "tv_entities")
data class TvEntity (
        @PrimaryKey
        @NotNull
        @ColumnInfo(name = "id")
        val id: Int,

        @ColumnInfo(name = "poster_path")
        val poster_path: String,

        @ColumnInfo(name = "backdrop_path")
        val backdrop_path: String,

        @ColumnInfo(name = "name")
        val name: String,

        @ColumnInfo(name = "first_air_date")
        val first_air_date: String,

        @ColumnInfo(name = "overview")
        val overview: String,

        @ColumnInfo(name = "vote_count")
        val vote_count: Int,

        @ColumnInfo(name = "vote_average")
        val vote_average: Double,

        @ColumnInfo(name = "isFav")
        var isFav: Boolean = false
)