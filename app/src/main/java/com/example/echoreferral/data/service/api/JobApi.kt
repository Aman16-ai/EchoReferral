package com.example.echoreferral.data.service.api

import com.example.echoreferral.data.model.response.Jobs.GetJobResponse
import com.example.echoreferral.data.model.response.Jobs.GetJobsResponse
import com.example.echoreferral.data.service.BaseApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface JobApi {

    @GET("/api/job/")
    suspend fun getJobs() : Response<GetJobsResponse>

    @GET("/api/job/{id}")
    suspend fun getJob(@Path("id") id:Int) : Response<GetJobResponse>
}