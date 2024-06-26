package com.example.echoreferral.data.repository.referral_request

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.echoreferral.data.model.entities.ReferralRequest
import com.example.echoreferral.data.model.entities.RequestStatusItem
import com.example.echoreferral.data.model.payload.referral_request.ReferralRequestPayload
import com.example.echoreferral.data.model.response.referral_request.ReferralRequestResponse
import com.example.echoreferral.utils.ApiState

interface ReferralRequestRepo {

    val referralRequestcreatedResponse : LiveData<ApiState<ReferralRequest?>>
    val allReferralRequests : LiveData<ApiState<List<ReferralRequest>?>>
    val requestsStatusResponse : LiveData<ApiState<List<RequestStatusItem>?>>

    suspend fun createRequest(referralRequestPayload: ReferralRequestPayload,token:String)
    suspend fun getAllRequestOfJob(token: String,jobId:Int)
    suspend fun getUserRequestsStatus(token: String)
}