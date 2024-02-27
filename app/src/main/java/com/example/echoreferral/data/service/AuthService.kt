package com.example.echoreferral.data.service

import com.example.echoreferral.data.service.api.AuthApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AuthService {
    val authServiceInstance : AuthApi
    init {
        val retrofit :Retrofit = Retrofit.Builder()
            .baseUrl(BaseApi.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        authServiceInstance = retrofit.create(AuthApi::class.java)
    }
}