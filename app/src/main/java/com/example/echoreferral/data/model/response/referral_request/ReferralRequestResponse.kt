package com.example.echoreferral.data.model.response.referral_request

import com.example.echoreferral.data.model.entities.ReferralRequest
import com.example.echoreferral.data.model.response.LoginApiResponse
import com.google.gson.annotations.SerializedName

data class ReferralRequestResponse(
    val status:Int?,
    val Response: PostReferralRequestData?
)  {
    data class PostReferralRequestData(
        val id: Int?,
        val job: Int?,
        val organisation: Int?,
        val pitch: String?,
        @SerializedName("created_at")
        val createdAt: String?,
        @SerializedName("updated_at")
        val updatedAt: String?
    )
}
