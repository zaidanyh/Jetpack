package com.dicoding.submission.data.remote

class ApiResponse<T> (val status: ApiStatus, val body: T, val message: String?) {
    companion object {
        fun <T> success(body: T): ApiResponse<T> = ApiResponse(ApiStatus.SUCCESS, body, null)
    }
}

enum class ApiStatus {
    SUCCESS,
    EMPTY,
    ERROR
}