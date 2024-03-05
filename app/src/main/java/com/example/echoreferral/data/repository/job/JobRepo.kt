package com.example.echoreferral.data.repository.job

import androidx.lifecycle.LiveData
import com.example.echoreferral.data.model.entities.Job
import com.example.echoreferral.utils.ApiState

interface JobRepo {

    val jobsResponse : LiveData<ApiState<List<Job>?>>
    suspend fun getAllJobs()
}