package com.example.echoreferral.ui.referral_request_inbox.job_requests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.echoreferral.data.repository.referral_request.ReferralRequestRepo
import com.example.echoreferral.data.repository.referral_request.ReferralRequestRepoImp
import kotlinx.coroutines.launch

class RequestViewModel:ViewModel() {

    private val requestRepo : ReferralRequestRepo = ReferralRequestRepoImp()
    val requestsResponse = requestRepo.allReferralRequests

    fun getAllRequestsByJobId(jobId:Int,token:String) {
        viewModelScope.launch {
            requestRepo.getAllRequestOfJob(token,jobId)
        }
    }
}