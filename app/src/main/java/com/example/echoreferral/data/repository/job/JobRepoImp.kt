package com.example.echoreferral.data.repository.job

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.echoreferral.data.model.entities.Job
import com.example.echoreferral.data.service.JobService
import com.example.echoreferral.data.service.api.JobApi
import com.example.echoreferral.utils.ApiState

class JobRepoImp : JobRepo {

    private var _jobResponse : MutableLiveData<ApiState<List<Job>?>> = MutableLiveData()
    override val jobsResponse : LiveData<ApiState<List<Job>?>>
        get() = _jobResponse

    override suspend fun getAllJobs() {
        try {
            _jobResponse.postValue(ApiState.Loading())
            val result = JobService
                .jobServiceInstance
                .getJobs()
                .body()
            _jobResponse.postValue(ApiState.Success(result?.Response))
        }
        catch (err : Error) {
            _jobResponse.postValue(ApiState.Error(message = err.toString()))
        }
    }

}