package com.example.echoreferral.data.model.response.Jobs

import com.example.echoreferral.data.model.entities.Job

data class GetJobResponse(
    val status:Int?,
    val Response: Job?
)
