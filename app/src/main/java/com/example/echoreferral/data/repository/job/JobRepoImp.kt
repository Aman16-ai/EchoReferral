package com.example.echoreferral.data.repository.job

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.echoreferral.data.model.entities.Job
import com.example.echoreferral.data.service.JobService
import com.example.echoreferral.data.service.api.JobApi
import com.example.echoreferral.utils.ApiState

class JobRepoImp : JobRepo {

    private var _jobsResponse : MutableLiveData<ApiState<List<Job>?>> = MutableLiveData()
    override val jobsResponse : LiveData<ApiState<List<Job>?>>
        get() = _jobsResponse


    private var _jobResponse : MutableLiveData<ApiState<Job?>> = MutableLiveData()

    override val jobResponse: LiveData<ApiState<Job?>>
        get() = _jobResponse
    override suspend fun getAllJobs() {
        try {
            _jobsResponse.postValue(ApiState.Loading())
            val result = JobService
                .jobServiceInstance
                .getJobs()
                .body()
            _jobsResponse.postValue(ApiState.Success(result?.Response))
        }
        catch (err : Error) {
            _jobsResponse.postValue(ApiState.Error(message = err.toString()))
        }
    }

    override suspend fun getJob(id: Int) {
        try {
            _jobResponse.postValue(ApiState.Loading())
            val result = JobService
                .jobServiceInstance
                .getJob(id)
                .body()
            _jobResponse.postValue(ApiState.Success(data=result?.Response))
        }
        catch (err : Error) {
            _jobResponse.postValue(ApiState.Error(message = err.toString()))
        }
    }

}