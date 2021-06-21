package com.dicoding.submission.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.submission.data.MyRepository
import com.dicoding.submission.data.local.entity.MoviesEntity
import com.dicoding.submission.ui.viewmodel.MyViewModel
import com.dicoding.submission.vo.Resource
import com.nhaarman.mockitokotlin2.verify
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
class MovieViewModelTest {
    private lateinit var myViewModel: MyViewModel
    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()
    @Mock
    private lateinit var myRepository: MyRepository
    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MoviesEntity>>>
    @Mock
    private lateinit var pagedList: PagedList<MoviesEntity>

    @Before
    fun setUp() {
        myViewModel = MyViewModel(myRepository)
    }
    @Test
    fun getMovies() {
        val dummyMovies = Resource.success(pagedList)
        `when`(dummyMovies.data?.size).thenReturn(10)
        val movies = MutableLiveData<Resource<PagedList<MoviesEntity>>>()
        movies.value = dummyMovies

        `when`(myRepository.getAllMovies("BEST")).thenReturn(movies)
        val moviesEntities = myViewModel.getMovies("BEST").value?.data
        verify(myRepository).getAllMovies("BEST")
        assertNotNull(moviesEntities)
        assertEquals(10, moviesEntities?.size)

        myViewModel.getMovies("BEST").observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}