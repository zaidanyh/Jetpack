package com.dicoding.submission.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.dicoding.submission.data.MyRepository

class MyViewModel(private val myRepository: MyRepository) : ViewModel() {
    fun getMovies(sort: String) = myRepository.getAllMovies(sort)
    fun getTvShows(sort: String) = myRepository.getAllTvShows(sort)
}