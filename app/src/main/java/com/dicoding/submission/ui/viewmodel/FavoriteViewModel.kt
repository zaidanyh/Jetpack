package com.dicoding.submission.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.dicoding.submission.data.MyRepository
import com.dicoding.submission.data.local.entity.MoviesEntity
import com.dicoding.submission.data.local.entity.TvEntity

class FavoriteViewModel(private val myRepository: MyRepository): ViewModel() {
    fun getFavMovies() = myRepository.getFavoriteMovies()
    fun getFavTvShows() = myRepository.getFavoriteTvShows()

    fun setFavMovie(moviesEntity: MoviesEntity) {
        val newState = !moviesEntity.isFav
        myRepository.setFavoriteMovie(moviesEntity, newState)
    }

    fun setFavTvShow(tvEntity: TvEntity) {
        val newState = !tvEntity.isFav
        myRepository.setFavoriteTvShow(tvEntity, newState)
    }
}