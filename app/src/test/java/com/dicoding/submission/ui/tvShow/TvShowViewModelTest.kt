package com.dicoding.submission.ui.tvShow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.submission.data.MyRepository
import com.dicoding.submission.data.local.entity.TvEntity
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
class TvShowViewModelTest {
    private lateinit var myViewModel: MyViewModel
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var myRepository: MyRepository
    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvEntity>>>
    @Mock
    private lateinit var pagedList: PagedList<TvEntity>

    @Before
    fun setUp() {
        myViewModel = MyViewModel(myRepository)
    }

    @Test
    fun getTvShow() {
        val dummyTvShow = Resource.success(pagedList)
        `when`(dummyTvShow.data?.size).thenReturn(10)
        val tvShows = MutableLiveData<Resource<PagedList<TvEntity>>>()
        tvShows.value = dummyTvShow

        `when`(myRepository.getAllTvShows("BEST")).thenReturn(tvShows)
        val tvEntities = myViewModel.getTvShows("BEST").value?.data
        verify(myRepository).getAllTvShows("BEST")
        assertNotNull(tvEntities)
        assertEquals(10, tvEntities?.size)

        myViewModel.getTvShows("BEST").observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}