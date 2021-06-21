package com.dicoding.submission.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseMovies(
    @SerializedName("results")
    val result: List<ResultMovie>

)

data class ResultMovie(
    @SerializedName("id")
    var id: Int,
    @SerializedName("backdrop_path")
    var backdrop_path: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("poster_path")
    var poster_path: String,
    @SerializedName("release_date")
    var release_date: String,
    @SerializedName("vote_count")
    var vote_count: Int,
    @SerializedName("vote_average")
    var vote_average: Double
)