package com.example.echoreferral.data.repository.referral_request

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.echoreferral.data.model.entities.ReferralRequest
import com.example.echoreferral.data.model.entities.RequestStatusItem
import com.example.echoreferral.data.model.payload.referral_request.ReferralRequestPayload
import com.example.echoreferral.data.service.ReferralRequestService
import com.example.echoreferral.utils.ApiState

class ReferralRequestRepoImp : ReferralRequestRepo{
    private var _allReferralRequests : MutableLiveData<ApiState<List<ReferralRequest>?>> = MutableLiveData()
    override val allReferralRequests: LiveData<ApiState<List<ReferralRequest>?>>
        get() = _allReferralRequests

    private var _referralRequestcreatedResponse : MutableLiveData<ApiState<ReferralRequest?>> = MutableLiveData()
    override val referralRequestcreatedResponse: LiveData<ApiState<ReferralRequest?>>
        get() = _referralRequestcreatedResponse

    private var _requestsStatusResponse : MutableLiveData<ApiState<List<RequestStatusItem>?>> = MutableLiveData()
    override val requestsStatusResponse: LiveData<ApiState<List<RequestStatusItem>?>>
        get() = _requestsStatusResponse


    override suspend fun createRequest(referralRequestPayload: ReferralRequestPayload,token:String) {
        try {
            _referralRequestcreatedResponse.postValue(ApiState.Loading())
            val result = ReferralRequestService.referralRequestInstance
                .postReferralRequest("Bearer "+token,referralRequestPayload)
                .body()
            val postReferralRequestData = result?.Response
            val referralRequest = ReferralRequest(
                id = postReferralRequestData?.id,
                job = postReferralRequestData?.job,
                organisation = postReferralRequestData?.organisation,
                pitch = postReferralRequestData?.pitch,
                createdAt = postReferralRequestData?.createdAt,
                updatedAt = postReferralRequestData?.updatedAt,
                score = null,
                candidate = null,
            )
            _referralRequestcreatedResponse.postValue(ApiState.Success(data=referralRequest))
        }
        catch (e : Exception) {
            _referralRequestcreatedResponse.postValue(ApiState.Error(message = "Something went wrong"))
        }
    }

    override suspend fun getAllRequestOfJob(token: String, jobId: Int) {
        try {
            _allReferralRequests.postValue(ApiState.Loading())
            val result = ReferralRequestService.referralRequestInstance
                .getAllReferralRequestOfJob(token = "Bearer $token", jobId = jobId)
                .body()
            _allReferralRequests.postValue(ApiState.Success(data = result?.Response))
        }
        catch (e:Exception) {
            _allReferralRequests.postValue(ApiState.Error(message = e.toString()))
        }
    }

    override suspend fun getUserRequestsStatus(token: String) {
        try {
            _requestsStatusResponse.postValue(ApiState.Loading())
            val result : List<RequestStatusItem>? = ReferralRequestService
                .referralRequestInstance
                .getUserReferralRequestStatus("Bearer $token")
                .body()
                ?.Response

            val statusMap = hashMapOf<String,Int>()
            statusMap["received"] = 0
            statusMap["send"] = 0
            statusMap["accepted"] = 0
            statusMap["rejected"] = 0

            if (result != null) {
                for(s in result) {
                    val status = s.status
                    val count = s.count
                    statusMap[status!!] = count!!
                }
            }

            val allStatusResponse : MutableList<RequestStatusItem> = ArrayList()
            for((s,c) in statusMap) {
                allStatusResponse.add(RequestStatusItem(status = s,count = c))
            }

            _requestsStatusResponse.postValue(ApiState.Success(data = allStatusResponse))
        }
        catch (e : Exception) {
            _requestsStatusResponse.postValue(ApiState.Error(message = e.toString()))
        }
    }


}