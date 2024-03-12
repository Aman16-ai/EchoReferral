package com.example.echoreferral.data.service.api

import com.example.echoreferral.data.model.payload.referral_request.ReferralRequestPayload
import com.example.echoreferral.data.model.response.referral_request.ReferralRequestResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ReferralRequestApi {

    @POST("/api/referral_request/")
    suspend fun postReferralRequest(@Header("Authorization") token:String,@Body payload: ReferralRequestPayload) : Response<ReferralRequestResponse>
}