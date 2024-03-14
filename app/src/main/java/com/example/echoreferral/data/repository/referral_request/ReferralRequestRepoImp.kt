package com.example.echoreferral.data.repository.referral_request

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.echoreferral.data.model.entities.ReferralRequest
import com.example.echoreferral.data.model.payload.referral_request.ReferralRequestPayload
import com.example.echoreferral.data.service.ReferralRequestService
import com.example.echoreferral.utils.ApiState

class ReferralRequestRepoImp : ReferralRequestRepo{
    private var _allReferralRequests : MutableLiveData<ApiState<List<ReferralRequest>>> = MutableLiveData()
    override val allReferralRequests: LiveData<ApiState<List<ReferralRequest>>>
        get() = _allReferralRequests

    private var _referralRequestcreatedResponse : MutableLiveData<ApiState<ReferralRequest?>> = MutableLiveData()
    override val referralRequestcreatedResponse: LiveData<ApiState<ReferralRequest?>>
        get() = _referralRequestcreatedResponse


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


}