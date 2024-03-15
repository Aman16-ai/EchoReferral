package com.example.echoreferral.data.service.api

import com.example.echoreferral.data.model.payload.referral_request.ReferralRequestPayload
import com.example.echoreferral.data.model.response.referral_request.GetAllReferralRequestResponse
import com.example.echoreferral.data.model.response.referral_request.ReferralRequestResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ReferralRequestApi {

    @POST("/api/referral_request/")
    suspend fun postReferralRequest(@Header("Authorization") token:String,@Body payload: ReferralRequestPayload) : Response<ReferralRequestResponse>

    @GET("/api/referral_request")
    suspend fun getAllReferralRequestOfJob(
        @Header("Authorization") token: String,
        @Query("job__id") jobId:Int
    ) : Response<GetAllReferralRequestResponse>
}