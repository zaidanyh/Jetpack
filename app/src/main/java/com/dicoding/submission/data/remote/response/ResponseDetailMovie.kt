package com.dicoding.submission.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseDetailMovie(

        @SerializedName("id")
        val id: Int,
        @SerializedName("backdrop_path")
        val backdrop_path: String,
        @SerializedName("genres")
        val genres: List<GenreMovie>,
        @SerializedName("title")
        val title: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("poster_path")
        val poster_path: String,
        @SerializedName("release_date")
        val release_date: String,
        @SerializedName("vote_count")
        val vote_count: Int,
        @SerializedName("vote_average")
        val vote_average: Double
)

data class GenreMovie(
        @SerializedName("name")
        val name: String
)
