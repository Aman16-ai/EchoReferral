package com.example.echoreferral.data.model.response.referral_request

import com.example.echoreferral.data.model.entities.ReferralRequest
import com.example.echoreferral.data.model.response.LoginApiResponse

data class ReferralRequestResponse(
    val status:Int?,
    val Response: ReferralRequest?
)
