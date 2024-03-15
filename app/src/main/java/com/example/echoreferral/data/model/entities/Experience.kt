package com.example.echoreferral.data.model.entities

import com.google.gson.annotations.SerializedName

data class Experience(
    @SerializedName("id") val id: Int?,
    @SerializedName("start_date") val startDate: String?,
    @SerializedName("end_date") val endDate: String?,
    @SerializedName("position") val position: String?,
    @SerializedName("location") val location: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("userProfile") val userProfile: Int?,
    @SerializedName("organisation") val organisation: Int?
)