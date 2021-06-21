package com.dicoding.submission.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.submission.data.local.entity.MoviesEntity
import com.dicoding.submission.data.local.entity.TvEntity
import com.dicoding.submission.vo.Resource

interface MyRepositoryDataSource {
    fun getAllMovies(sort: String): LiveData<Resource<PagedList<MoviesEntity>>>

    fun getDetailMovie(movieId: Int): LiveData<Resource<MoviesEntity>>

    fun getFavoriteMovies(): LiveData<PagedList<MoviesEntity>>

    fun setFavoriteMovie(movie: MoviesEntity, state: Boolean)

    fun getAllTvShows(sort: String): LiveData<Resource<PagedList<TvEntity>>>

    fun getDetailTvShow(tvShowId: Int): LiveData<Resource<TvEntity>>

    fun getFavoriteTvShows(): LiveData<PagedList<TvEntity>>

    fun setFavoriteTvShow(tvShow: TvEntity, state: Boolean)
}