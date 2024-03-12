package com.example.echoreferral.data.model.entities

import com.google.gson.annotations.SerializedName

data class ReferralRequest(
    val id: Int?,
    val job: Int?,
    val organisation: Int?,
    val pitch: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
)
