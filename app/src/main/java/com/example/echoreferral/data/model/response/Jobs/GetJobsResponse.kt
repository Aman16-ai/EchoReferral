package com.example.echoreferral.data.model.response.Jobs

import com.example.echoreferral.data.model.entities.Job
import com.example.echoreferral.data.model.response.LoginApiResponse

data class GetJobsResponse(
    val status:Int?,
    val Response: List<Job>?
)
