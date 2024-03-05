package com.example.echoreferral.data.service


import com.example.echoreferral.data.service.api.JobApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object JobService {
    val jobServiceInstance : JobApi
    init {
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(BaseApi.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        jobServiceInstance = retrofit.create(JobApi::class.java)
    }
}