package com.dicoding.submission.di

import android.content.Context
import com.dicoding.submission.data.MyRepository
import com.dicoding.submission.data.local.LocalDataSource
import com.dicoding.submission.data.local.room.MovieTvDatabase
import com.dicoding.submission.data.remote.RemoteDataSource
import com.dicoding.submission.utils.AppExecutors

object Injection {
    fun provide(context: Context): MyRepository {
        val database = MovieTvDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.movieTvDao())
        val appExecutors = AppExecutors()

        return MyRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}