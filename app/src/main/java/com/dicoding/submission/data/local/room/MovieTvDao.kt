package com.dicoding.submission.data.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.dicoding.submission.data.local.entity.MoviesEntity
import com.dicoding.submission.data.local.entity.TvEntity

@Dao
interface MovieTvDao {
    @RawQuery(observedEntities = [MoviesEntity::class])
    fun getMovies(query: SimpleSQLiteQuery): DataSource.Factory<Int, MoviesEntity>

    @RawQuery(observedEntities = [TvEntity::class])
    fun getTvShows(query: SimpleSQLiteQuery): DataSource.Factory<Int, TvEntity>

    @Query("SELECT * FROM movie_entities WHERE id = :id")
    fun getMovieById(id: Int): LiveData<MoviesEntity>

    @Query("SELECT * FROM tv_entities WHERE id = :id")
    fun getTvShowById(id: Int): LiveData<TvEntity>

    @Query("SELECT * FROM movie_entities WHERE isFav = 1")
    fun getFavMovies(): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM tv_entities WHERE isFav = 1")
    fun getFavTvShows(): DataSource.Factory<Int, TvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MoviesEntity>)

    @Update
    fun updateMovie(movie: MoviesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow: List<TvEntity>)

    @Update
    fun updateTvShow(tvEntity: TvEntity)
}