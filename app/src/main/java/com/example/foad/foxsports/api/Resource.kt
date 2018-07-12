package com.example.foad.foxsports.api


sealed class Resource<T>(val data: T?) {


    class Success<T> (successData: T?) : Resource<T>(successData)
    class Error<T>(val message: String?) : Resource<T>(null)
    class Loading<T> : Resource<T>(null)
}