package com.dicoding.submission.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseTvShow(
    @SerializedName("results")
    val results: List<ResultTvShow>
)
data class ResultTvShow(
    @SerializedName("id")
    var id: Int,
    @SerializedName("poster_path")
    var poster_path: String,
    @SerializedName("backdrop_path")
    var backdrop_path: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("first_air_date")
    var first_air_date: String,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("vote_count")
    var vote_count: Int,
    @SerializedName("vote_average")
    var vote_average: Double
)
