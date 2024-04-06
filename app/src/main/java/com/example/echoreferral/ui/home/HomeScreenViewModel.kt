package com.example.echoreferral.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.echoreferral.data.repository.referral_request.ReferralRequestRepo
import com.example.echoreferral.data.repository.referral_request.ReferralRequestRepoImp
import kotlinx.coroutines.launch

class HomeScreenViewModel:ViewModel() {

    private val referralRequestRepo : ReferralRequestRepo = ReferralRequestRepoImp()
    var requestStatus = referralRequestRepo.requestsStatusResponse

    fun getUserRequestsStatus(token:String) {
        viewModelScope.launch {
            referralRequestRepo.getUserRequestsStatus(token)
        }
    }
}