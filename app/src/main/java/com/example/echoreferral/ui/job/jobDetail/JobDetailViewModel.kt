package com.example.echoreferral.ui.job.jobDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.echoreferral.data.repository.job.JobRepo
import com.example.echoreferral.data.repository.job.JobRepoImp
import kotlinx.coroutines.launch

class JobDetailViewModel:ViewModel() {

    private val jobRepo : JobRepo = JobRepoImp()
    var jobResponse = jobRepo.jobResponse

    fun getJob(id:Int) {
        viewModelScope.launch {
            jobRepo.getJob(id)
        }
    }
}