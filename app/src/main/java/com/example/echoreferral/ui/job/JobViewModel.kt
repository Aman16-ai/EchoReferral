package com.example.echoreferral.ui.job

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.echoreferral.data.model.entities.UserProfile
import com.example.echoreferral.data.model.payload.referral_request.ReferralRequestPayload
import com.example.echoreferral.data.repository.job.JobRepo
import com.example.echoreferral.data.repository.job.JobRepoImp
import com.example.echoreferral.data.repository.referral_request.ReferralRequestRepo
import com.example.echoreferral.data.repository.referral_request.ReferralRequestRepoImp
import kotlinx.coroutines.launch

class JobViewModel:ViewModel() {
    private val jobRepo : JobRepo = JobRepoImp()
    val jobResponse = jobRepo.jobsResponse


    fun getAllJobs() {
        viewModelScope.launch {
            jobRepo.getAllJobs()
        }
    }


    fun getAllJobsOfCurrOrgansiationofUser(userProfile: UserProfile) {
        viewModelScope.launch {
            val userCurrOrg = userProfile.curr_orgs
            if(userCurrOrg?.isEmpty() == false) {
                userCurrOrg[0].organisation?.let { jobRepo.getJobsByOrganisation(it) }
            }
        }
    }

    fun getRecentJobs() {
        viewModelScope.launch {
            jobRepo.getRecentJobs()
        }
    }

}