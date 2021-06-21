package com.dicoding.submission.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.submission.data.remote.response.*
import com.dicoding.submission.network.ApiConfig
import com.dicoding.submission.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    fun getMovies(): LiveData<ApiResponse<List<ResultMovie>>> {
        EspressoIdlingResource.increment()
        val movies = MutableLiveData<ApiResponse<List<ResultMovie>>>()
        val client = ApiConfig.getRequestApi().getMovie()
        client.enqueue(object: Callback<ResponseMovies> {
            override fun onResponse(call: Call<ResponseMovies>, response: Response<ResponseMovies>) {
                if (response.isSuccessful) {
                    movies.value = ApiResponse.success(response.body()?.result as List<ResultMovie>)
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ResponseMovies>, t: Throwable) {
                Log.e("RemoteDataSource", "getMovies: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return movies
    }

    fun getTvShow(): LiveData<ApiResponse<List<ResultTvShow>>> {
        EspressoIdlingResource.increment()
        val tvShows = MutableLiveData<ApiResponse<List<ResultTvShow>>>()
        val client = ApiConfig.getRequestApi().getTvShow()

        client.enqueue(object: Callback<ResponseTvShow> {
            override fun onResponse(call: Call<ResponseTvShow>, response: Response<ResponseTvShow>) {
                if (response.isSuccessful) {
                    tvShows.value = ApiResponse.success(response.body()?.results as List<ResultTvShow>)
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ResponseTvShow>, t: Throwable) {
                Log.e("RemoteDataSource", "getTvShow: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return tvShows
    }

    fun getMovieById(id: Int): LiveData<ApiResponse<ResponseDetailMovie>> {
        EspressoIdlingResource.increment()
        val detailMovie = MutableLiveData<ApiResponse<ResponseDetailMovie>>()
        val client = ApiConfig.getRequestApi().getMovieDetail(id)

        client.enqueue(object: Callback<ResponseDetailMovie> {
            override fun onResponse(call: Call<ResponseDetailMovie>, response: Response<ResponseDetailMovie>) {
                if (response.isSuccessful) {
                    detailMovie.postValue(ApiResponse.success(response.body() as ResponseDetailMovie))
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ResponseDetailMovie>, t: Throwable) {
                Log.e("RemoteDataSource", "getDetailMovie: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return detailMovie
    }

    fun getTvShowById(id: Int): LiveData<ApiResponse<ResponseDetailTv>> {
        EspressoIdlingResource.increment()
        val detailTvShow = MutableLiveData<ApiResponse<ResponseDetailTv>>()
        val client = ApiConfig.getRequestApi().getTvDetail(id)

        client.enqueue(object: Callback<ResponseDetailTv> {
            override fun onResponse(call: Call<ResponseDetailTv>, response: Response<ResponseDetailTv>) {
                if (response.isSuccessful) {
                    detailTvShow.postValue(ApiResponse.success(response.body() as ResponseDetailTv))
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ResponseDetailTv>, t: Throwable) {
                Log.e("RemoteDataSource", "getDetailTvShow: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return detailTvShow
    }
}