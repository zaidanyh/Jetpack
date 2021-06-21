package com.dicoding.submission.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicoding.submission.data.local.entity.MoviesEntity
import com.dicoding.submission.data.local.entity.TvEntity
import com.dicoding.submission.data.local.room.MovieTvDao
import com.dicoding.submission.utils.SortUtils
import com.dicoding.submission.utils.SortUtils.MOVIE_ENTITY
import com.dicoding.submission.utils.SortUtils.TV_SHOW_ENTITY

class LocalDataSource(private val mMovieTvDao: MovieTvDao) {
    companion object {
        private var INSTANCE: LocalDataSource?= null

        fun getInstance(movieTvDao: MovieTvDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieTvDao)
    }

    fun getAllMovies(sort: String): DataSource.Factory<Int, MoviesEntity> = mMovieTvDao.getMovies(SortUtils.getSortedQuery(sort, MOVIE_ENTITY))

    fun getMovieById(id: Int): LiveData<MoviesEntity> = mMovieTvDao.getMovieById(id)

    fun getFavMovies(): DataSource.Factory<Int, MoviesEntity> = mMovieTvDao.getFavMovies()

    fun getAllTvShows(sort: String): DataSource.Factory<Int, TvEntity> = mMovieTvDao.getTvShows(SortUtils.getSortedQuery(sort, TV_SHOW_ENTITY))

    fun getTvShowById(id: Int): LiveData<TvEntity> = mMovieTvDao.getTvShowById(id)

    fun getFavTvShows(): DataSource.Factory<Int, TvEntity> = mMovieTvDao.getFavTvShows()

    fun insertMovies(movies: List<MoviesEntity>) = mMovieTvDao.insertMovies(movies)

    fun setFavoriteMovie(movie: MoviesEntity, newState: Boolean) {
        movie.isFav = newState
        mMovieTvDao.updateMovie(movie)
    }

    fun updateMovie(movie: MoviesEntity, newState: Boolean) {
        movie.isFav = newState
        mMovieTvDao.updateMovie(movie)
    }

    fun insertTvShows(tvShows: List<TvEntity>) = mMovieTvDao.insertTvShow(tvShows)

    fun setFavoriteTvShow(tvShow: TvEntity, newState: Boolean) {
        tvShow.isFav = newState
        mMovieTvDao.updateTvShow(tvShow)
    }

    fun updateTvShow(tvShow: TvEntity, newState: Boolean) {
        tvShow.isFav = newState
        mMovieTvDao.updateTvShow(tvShow)
    }
}