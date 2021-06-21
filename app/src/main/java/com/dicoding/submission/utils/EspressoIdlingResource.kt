package com.dicoding.submission.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {

    private const val RESOURCE = "GLOBAL"
    private val testIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() = testIdlingResource.increment()
    fun decrement() = testIdlingResource.decrement()
    fun getEspressoIdIdlingResource(): IdlingResource = testIdlingResource
}