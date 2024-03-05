package com.example.echoreferral.ui.job

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.echoreferral.data.repository.job.JobRepo
import com.example.echoreferral.data.repository.job.JobRepoImp
import kotlinx.coroutines.launch

class JobViewModel:ViewModel() {
    private val jobRepo : JobRepo = JobRepoImp()
    val jobResponse = jobRepo.jobsResponse

    fun getAllJobs() {
        viewModelScope.launch {
            jobRepo.getAllJobs()
        }
    }
}