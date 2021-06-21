package com.dicoding.submission.data.remote.response

data class ResponseDetailTv (
        var id: Int,
        var backdrop_path: String,
        var created_by: List<Creator>,
        var first_air_date: String,
        var genres: List<GenreTv>,
        var name: String,
        var overview: String,
        var poster_path: String,
        var vote_count: Int,
        var vote_average: Double
)

data class Creator (
    var name: String
)

data class GenreTv (
    var name: String
)
