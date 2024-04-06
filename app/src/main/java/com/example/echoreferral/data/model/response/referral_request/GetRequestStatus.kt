package com.example.echoreferral.data.model.response.referral_request

import com.example.echoreferral.data.model.entities.ReferralRequest
import com.example.echoreferral.data.model.entities.RequestStatusItem

data class GetRequestStatus(
    val status:Int?,
    val Response: List<RequestStatusItem>?
)
