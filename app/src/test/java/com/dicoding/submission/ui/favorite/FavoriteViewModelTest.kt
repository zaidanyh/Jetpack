package com.dicoding.submission.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.submission.data.MyRepository
import com.dicoding.submission.data.local.entity.MoviesEntity
import com.dicoding.submission.data.local.entity.TvEntity
import com.dicoding.submission.ui.viewmodel.FavoriteViewModel
import com.dicoding.submission.utils.DataDummy
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
class FavoriteViewModelTest {

    private lateinit var favoriteViewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var myRepository: MyRepository

    @Mock
    private lateinit var movieObserver: Observer<PagedList<MoviesEntity>>
    @Mock
    private lateinit var tvShowObserver: Observer<PagedList<TvEntity>>

    @Mock
    private lateinit var moviePagedList: PagedList<MoviesEntity>
    @Mock
    private lateinit var tvShowPagedList: PagedList<TvEntity>

    @Before
    fun setUp() {
        favoriteViewModel = FavoriteViewModel(myRepository)
    }

    @Test
    fun getFavMovies() {
        val dummyMovie = moviePagedList
        `when`(dummyMovie.size).thenReturn(10)
        val movieEntities = MutableLiveData<PagedList<MoviesEntity>>()
        movieEntities.value = dummyMovie

        `when`(myRepository.getFavoriteMovies()).thenReturn(movieEntities)
        val movies = favoriteViewModel.getFavMovies().value
        verify(myRepository).getFavoriteMovies()
        assertNotNull(movies)
        assertEquals(10, movies?.size)

        favoriteViewModel.getFavMovies().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun setFavMovie() {
        favoriteViewModel.setFavMovie(DataDummy.generateDetailMovie())
        verify(myRepository).setFavoriteMovie(DataDummy.generateDetailMovie(), true)
        verifyNoMoreInteractions(myRepository)
    }

    @Test
    fun getFavTvShow() {
        val dummyTv = tvShowPagedList
        `when`(dummyTv.size).thenReturn(10)
        val tvEntities = MutableLiveData<PagedList<TvEntity>>()
        tvEntities.value = dummyTv

        `when`(myRepository.getFavoriteTvShows()).thenReturn(tvEntities)
        val tvShows = favoriteViewModel.getFavTvShows().value
        verify(myRepository).getFavoriteTvShows()
        assertNotNull(tvShows)
        assertEquals(10, tvShows?.size)

        favoriteViewModel.getFavTvShows().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTv)
    }

    @Test
    fun setFavTvShow() {
        favoriteViewModel.setFavTvShow(DataDummy.generateDetailTv())
        verify(myRepository).setFavoriteTvShow(DataDummy.generateDetailTv(), true)
        verifyNoMoreInteractions(myRepository)
    }
}