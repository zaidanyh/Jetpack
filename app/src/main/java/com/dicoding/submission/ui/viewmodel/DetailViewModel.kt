package com.dicoding.submission.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.submission.data.MyRepository
import com.dicoding.submission.data.local.entity.MoviesEntity
import com.dicoding.submission.data.local.entity.TvEntity
import com.dicoding.submission.vo.Resource

class DetailViewModel(private val myRepository: MyRepository): ViewModel() {
    companion object {
        const val MOVIE = "movie"
        const val TV_SHOW = "tvShow"
    }

    private lateinit var detailMovie: LiveData<Resource<MoviesEntity>>
    private lateinit var detailTvShow: LiveData<Resource<TvEntity>>

    fun setData(id: Int, category: String) {
        when(category) {
            MOVIE -> detailMovie = myRepository.getDetailMovie(id)
            TV_SHOW -> detailTvShow = myRepository.getDetailTvShow(id)
        }
    }

    fun setFavoriteMovie() {
        val resource = detailMovie.value
        if (resource?.data != null) {
            val newState = !resource.data.isFav
            myRepository.setFavoriteMovie(resource.data, newState)
        }
    }

    fun setFavoriteTvShow() {
        val resource = detailTvShow.value
        if (resource?.data != null) {
            val newState = !resource.data.isFav
            myRepository.setFavoriteTvShow(resource.data, newState)
        }
    }

    fun getDetailMovie() = detailMovie
    fun getDetailTvShow() = detailTvShow
}