package com.example.echoreferral.utils

sealed class ApiState<T>(val data:T?,val message:String?) {

    class Success<T>(data: T) : ApiState<T>(data=data, message=null)
    class Error<T>(message:String) :ApiState<T>(data = null,message=message)
    class Loading<T>: ApiState<T>(data=null, message = null)
}