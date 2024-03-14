package com.example.echoreferral.ui.job.jobDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.echoreferral.data.model.payload.referral_request.ReferralRequestPayload
import com.example.echoreferral.data.repository.job.JobRepo
import com.example.echoreferral.data.repository.job.JobRepoImp
import com.example.echoreferral.data.repository.referral_request.ReferralRequestRepo
import com.example.echoreferral.data.repository.referral_request.ReferralRequestRepoImp
import kotlinx.coroutines.launch

class JobDetailViewModel:ViewModel() {

    private val jobRepo : JobRepo = JobRepoImp()
    var jobResponse = jobRepo.jobResponse

    private val referralRequestRepo : ReferralRequestRepo = ReferralRequestRepoImp()
    val referralRequestCreatedResponse = referralRequestRepo.referralRequestcreatedResponse
    fun getJob(id:Int) {
        viewModelScope.launch {
            jobRepo.getJob(id)
        }
    }

    fun createReferralRequest(payload: ReferralRequestPayload, token:String) {
        viewModelScope.launch {
            referralRequestRepo.createRequest(
                referralRequestPayload = payload,
                token = token
            )
        }
    }
}