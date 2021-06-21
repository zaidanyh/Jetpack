package com.dicoding.submission.utils.helper

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import kotlin.jvm.Throws

class Authorization: Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyOTJiMzM1MTVjMGZlZmM4MWZjZGY4YjRjOWMwYWNhYiIsInN1YiI6IjVmOWMxOWViNWYyZGIxMDAzOTIwYTQ4MSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.8OgHsfu_aUgE50MoppR5nIpHaaMrLd7FDW4el_OTxa0")
            .addHeader("Content-Type", "application/json;charset=utf-8")
        val request = builder.build()
        return chain.proceed(request)
    }
}