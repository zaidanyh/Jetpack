package com.dicoding.submission.utils

class InstantAppExecutors: AppExecutors {
    companion object {
        val instant = Runnable::run
    }

    constructor(): super(
        instant, instant, instant
            )
}