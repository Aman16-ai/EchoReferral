package com.example.echoreferral.data.model.payload.referral_request

import com.example.echoreferral.data.model.entities.Organisation

data class ReferralRequestPayload(
    val job:Int?,
    val organisation: Int?,
    val pitch:String?
)
