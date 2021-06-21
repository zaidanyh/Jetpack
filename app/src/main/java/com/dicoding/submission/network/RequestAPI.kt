package com.dicoding.submission.network

import com.dicoding.submission.data.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RequestAPI {

    @GET("movie/upcoming")
    fun getMovie(): Call<ResponseMovies>

    @GET("tv/on_the_air")
    fun getTvShow(): Call<ResponseTvShow>

    @GET("movie/{id}")
    fun getMovieDetail(@Path("id") id: Int): Call<ResponseDetailMovie>

    @GET("tv/{id}")
    fun getTvDetail(@Path("id") id: Int): Call<ResponseDetailTv>
}