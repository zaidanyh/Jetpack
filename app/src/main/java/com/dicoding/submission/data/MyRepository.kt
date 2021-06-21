package com.dicoding.submission.data

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.dicoding.submission.data.local.LocalDataSource
import com.dicoding.submission.data.local.entity.MoviesEntity
import com.dicoding.submission.data.local.entity.TvEntity
import com.dicoding.submission.data.remote.ApiResponse
import com.dicoding.submission.data.remote.RemoteDataSource
import com.dicoding.submission.data.remote.response.*
import com.dicoding.submission.utils.AppExecutors
import com.dicoding.submission.vo.Resource

class MyRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
    ): MyRepositoryDataSource {

    companion object {
        @Volatile
        private var instance:MyRepository ?= null

        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): MyRepository =
            instance ?: synchronized(this) {
                instance ?: MyRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllMovies(sort: String): LiveData<Resource<PagedList<MoviesEntity>>> {
        return object : NetworkBoundResource<PagedList<MoviesEntity>, List<ResultMovie>>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<MoviesEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllMovies(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<MoviesEntity>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultMovie>>> = remoteDataSource.getMovies()

            override fun saveCallResult(data: List<ResultMovie>) {
                val movieList = ArrayList<MoviesEntity>()
                for (response in data) {
                    val movie = MoviesEntity(
                        id = response.id,
                        backdrop_path = response.backdrop_path,
                        title = response.title,
                        overview = response.overview,
                        poster_path = response.poster_path,
                        release_date = response.release_date,
                        vote_count = response.vote_count,
                        vote_average = response.vote_average,
                        isFav = false
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(movieId: Int): LiveData<Resource<MoviesEntity>> {
        return object: NetworkBoundResource<MoviesEntity, ResponseDetailMovie>(appExecutors) {
            override fun loadFromDb(): LiveData<MoviesEntity> = localDataSource.getMovieById(movieId)

            override fun shouldFetch(data: MoviesEntity?): Boolean = data != null

            override fun createCall(): LiveData<ApiResponse<ResponseDetailMovie>> = remoteDataSource.getMovieById(movieId)

            override fun saveCallResult(data: ResponseDetailMovie) {
                val movie = MoviesEntity(
                    id = data.id,
                    backdrop_path = data.backdrop_path,
                    title = data.title,
                    overview = data.overview,
                    poster_path = data.poster_path,
                    release_date = data.release_date,
                    vote_average = data.vote_average,
                    vote_count = data.vote_count,
                    isFav = false
                )
                localDataSource.updateMovie(movie, false)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MoviesEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavMovies(), config).build()
    }

    override fun setFavoriteMovie(movie: MoviesEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovie(movie, state)
        }
    }

    override fun getAllTvShows(sort: String): LiveData<Resource<PagedList<TvEntity>>> {
        return object: NetworkBoundResource<PagedList<TvEntity>, List<ResultTvShow>>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<TvEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvShows(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<TvEntity>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultTvShow>>> = remoteDataSource.getTvShow()

            override fun saveCallResult(data: List<ResultTvShow>) {
                val tvShowList = ArrayList<TvEntity>()
                for (response in data) {
                    val tvShow = TvEntity(
                        id = response.id,
                        poster_path = response.poster_path,
                        backdrop_path = response.backdrop_path,
                        name = response.name,
                        first_air_date = response.first_air_date,
                        overview = response.overview,
                        vote_average = response.vote_average,
                        vote_count = response.vote_count,
                        isFav = false
                    )
                    tvShowList.add(tvShow)
                }
                localDataSource.insertTvShows(tvShowList)
            }
        }.asLiveData()
    }

    override fun getDetailTvShow(tvShowId: Int): LiveData<Resource<TvEntity>> {
        return object: NetworkBoundResource<TvEntity, ResponseDetailTv>(appExecutors) {
            override fun loadFromDb(): LiveData<TvEntity> = localDataSource.getTvShowById(tvShowId)

            override fun shouldFetch(data: TvEntity?): Boolean = data != null

            override fun createCall(): LiveData<ApiResponse<ResponseDetailTv>> = remoteDataSource.getTvShowById(tvShowId)

            override fun saveCallResult(data: ResponseDetailTv) {
                val tvShow = TvEntity(
                    id = data.id,
                    poster_path = data.poster_path,
                    backdrop_path = data.backdrop_path,
                    name = data.name,
                    first_air_date = data.first_air_date,
                    overview = data.overview,
                    vote_average = data.vote_average,
                    vote_count = data.vote_count,
                    isFav = false
                )
                localDataSource.updateTvShow(tvShow, false)
            }
        }.asLiveData()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavTvShows(), config).build()
    }

    override fun setFavoriteTvShow(tvShow: TvEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteTvShow(tvShow, state)
        }
    }
}