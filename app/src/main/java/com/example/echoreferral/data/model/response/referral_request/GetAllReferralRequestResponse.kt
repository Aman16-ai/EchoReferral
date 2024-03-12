package com.example.echoreferral.data.model.response.referral_request

import com.example.echoreferral.data.model.entities.ReferralRequest

data class GetAllReferralRequestResponse(
    val status:Int?,
    val Response: List<ReferralRequest>?
)
