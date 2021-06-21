package com.dicoding.submission.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dicoding.submission.data.local.LocalDataSource
import com.dicoding.submission.data.local.entity.MoviesEntity
import com.dicoding.submission.data.local.entity.TvEntity
import com.dicoding.submission.data.remote.RemoteDataSource
import com.dicoding.submission.utils.DataDummy
import com.dicoding.submission.utils.InstantAppExecutors
import com.dicoding.submission.utils.LiveDataTestUtil
import com.dicoding.submission.utils.PagedListUtils
import com.dicoding.submission.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class MyRepositoryTest {

    private lateinit var myRepository: FakeMyRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private val remote = mock(RemoteDataSource::class.java)
    @Mock
    private val local = mock(LocalDataSource::class.java)
    @Mock
    private val appExecutors = mock(InstantAppExecutors::class.java)

    private val movieResponse = DataDummy.generateRemoteDummyMovie()
    private val dummyMovieDetail = DataDummy.generateDetailMovie()
    private val dummyMovieId = movieResponse[3].id

    private val tvShowResponse = DataDummy.generateRemoteDummyTvShow()
    private val dummyTvShowDetail = DataDummy.generateDetailTv()
    private val dummyTvShowId = tvShowResponse[3].id

    @Before
    fun setUp() {
        myRepository = FakeMyRepository(remote, local, appExecutors)
    }

    @Test
    fun getMovies() {
        val dataSource = mock(DataSource.Factory::class.java)
        `when`(local.getAllMovies("BEST")).thenReturn(dataSource as DataSource.Factory<Int,MoviesEntity>)
        myRepository.getAllMovies("BEST")

        val movieEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateMovie()))
        verify(local).getAllMovies("BEST")
        assertNotNull(movieEntities)
        assertEquals(movieResponse.size, movieEntities.data?.size)
    }

    @Test
    fun getDetailMovie() {
        val detailMovie = MutableLiveData<MoviesEntity>()
        detailMovie.value = dummyMovieDetail
        `when`(local.getMovieById(dummyMovieId)).thenReturn(detailMovie)

        val moviesEntity = LiveDataTestUtil.getValue(local.getMovieById(dummyMovieId))
        verify(local).getMovieById(dummyMovieId)
        assertNotNull(moviesEntity)
        assertEquals(dummyMovieDetail.id, moviesEntity.id)
    }

    @Test
    fun getMovieFavorites() {
        val dataSource = mock(DataSource.Factory::class.java)
        `when`(local.getFavMovies()).thenReturn(dataSource as DataSource.Factory<Int, MoviesEntity>)
        myRepository.getFavoriteMovies()

        val movieEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateMovie()))
        verify(local).getFavMovies()
        assertNotNull(movieEntities)
        assertEquals(movieResponse.size, movieEntities.data?.size)
    }

    @Test
    fun setFavoriteMovie() {
        myRepository.setFavoriteMovie(DataDummy.generateDetailMovie(), true)
        verify(local).setFavoriteMovie(DataDummy.generateDetailMovie(), true)
        verifyNoMoreInteractions(local)
    }


    @Test
    fun getTvShow() {
        val dataSource = mock(DataSource.Factory::class.java)
        `when`(local.getAllTvShows("BEST")).thenReturn(dataSource as DataSource.Factory<Int, TvEntity>)
        myRepository.getAllTvShows("BEST")

        val tvEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateTV()))
        verify(local).getAllTvShows("BEST")
        assertNotNull(tvEntities)
        assertEquals(tvShowResponse.size, tvEntities.data?.size)
    }

    @Test
    fun getDetailTvShow() {
        val detailTvShow = MutableLiveData<TvEntity>()
        detailTvShow.value = dummyTvShowDetail
        `when`(local.getTvShowById(dummyTvShowId)).thenReturn(detailTvShow)

        val tvEntity = LiveDataTestUtil.getValue(local.getTvShowById(dummyTvShowId))
        verify(local).getTvShowById(dummyTvShowId)
        assertNotNull(tvEntity)
        assertEquals(dummyTvShowDetail.id, tvEntity.id)
    }

    @Test
    fun getTvShowFavorites() {
        val dataSource = mock(DataSource.Factory::class.java)
        `when`(local.getFavTvShows()).thenReturn(dataSource as DataSource.Factory<Int, TvEntity>)
        myRepository.getFavoriteTvShows()

        val tvEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateTV()))
        verify(local).getFavTvShows()
        assertNotNull(tvEntities)
        assertEquals(tvShowResponse.size, tvEntities.data?.size)
    }

    @Test
    fun setFavoriteTvShow() {
        myRepository.setFavoriteTvShow(DataDummy.generateDetailTv(), true)
        verify(local).setFavoriteTvShow(DataDummy.generateDetailTv(), true)
        verifyNoMoreInteractions(local)
    }
}