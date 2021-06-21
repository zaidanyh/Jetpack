package com.dicoding.submission.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.submission.data.MyRepository
import com.dicoding.submission.data.local.entity.MoviesEntity
import com.dicoding.submission.data.local.entity.TvEntity
import com.dicoding.submission.ui.viewmodel.DetailViewModel
import com.dicoding.submission.ui.viewmodel.DetailViewModel.Companion.MOVIE
import com.dicoding.submission.ui.viewmodel.DetailViewModel.Companion.TV_SHOW
import com.dicoding.submission.utils.DataDummy
import com.dicoding.submission.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var detailViewModel: DetailViewModel

    private val dummyMovie = DataDummy.generateDetailMovie()
    private val movieId = dummyMovie.id

    private val dummyTvShow = DataDummy.generateDetailTv()
    private val tvShowId = dummyTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var myRepository: MyRepository
    @Mock
    private lateinit var movieObserver: Observer<Resource<MoviesEntity>>
    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TvEntity>>

    @Before
    fun setupMovie() {
        detailViewModel = DetailViewModel(myRepository)
    }

    @Test
    fun getMovieDetail() {
        val dummyDetailMovie = Resource.success(dummyMovie)
        val movie = MutableLiveData<Resource<MoviesEntity>>()
        movie.value = dummyDetailMovie

        `when`(myRepository.getDetailMovie(movieId)).thenReturn(movie)

        detailViewModel.setData(movieId, MOVIE)
        val detailMovie = detailViewModel.getDetailMovie().value?.data
        verify(myRepository).getDetailMovie(movieId)
        assertNotNull(detailMovie)
        assertEquals(dummyMovie.id, detailMovie?.id)
        assertEquals(dummyMovie.title, detailMovie?.title)
        assertEquals(dummyMovie.overview, detailMovie?.overview)

        detailViewModel.getDetailMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyDetailMovie)
    }

    @Test
    fun setFavoriteMovie() {
        val dummyMovieDetail = Resource.success(DataDummy.generateDetailMovie())
        val movie = MutableLiveData<Resource<MoviesEntity>>()
        movie.value = dummyMovieDetail

        `when`(myRepository.getDetailMovie(movieId)).thenReturn(movie)

        detailViewModel.setData(movieId, MOVIE)
        detailViewModel.setFavoriteMovie()
        verify(myRepository).setFavoriteMovie(movie.value?.data as MoviesEntity, true)
        verifyNoMoreInteractions(movieObserver)
    }


    @Before
    fun setupTvShow() {
        detailViewModel = DetailViewModel(myRepository)
    }

    @Test
    fun getTvShowDetail() {
        val dummyDetailTvShow = Resource.success(dummyTvShow)
        val tvShow = MutableLiveData<Resource<TvEntity>>()
        tvShow.value = dummyDetailTvShow

        `when`(myRepository.getDetailTvShow(tvShowId)).thenReturn(tvShow)

        detailViewModel.setData(tvShowId, TV_SHOW)
        val detailTvShow = detailViewModel.getDetailTvShow().value?.data
        verify(myRepository).getDetailTvShow(tvShowId)
        assertNotNull(detailTvShow)
        assertEquals(dummyTvShow.id, detailTvShow?.id)
        assertEquals(dummyTvShow.name, detailTvShow?.name)
        assertEquals(dummyTvShow.overview, detailTvShow?.overview)

        detailViewModel.getDetailTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyDetailTvShow)
    }

    @Test
    fun setFavoriteTvShow() {
        val dummyTvDetail = Resource.success(DataDummy.generateDetailTv())
        val tvShow = MutableLiveData<Resource<TvEntity>>()
        tvShow.value = dummyTvDetail

        `when`(myRepository.getDetailTvShow(tvShowId)).thenReturn(tvShow)

        detailViewModel.setData(tvShowId, TV_SHOW)
        detailViewModel.setFavoriteTvShow()
        verify(myRepository).setFavoriteTvShow(tvShow.value?.data as TvEntity, true)
    }
}