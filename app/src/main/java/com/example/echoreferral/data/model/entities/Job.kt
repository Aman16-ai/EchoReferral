package com.example.echoreferral.data.model.entities

import com.google.gson.annotations.SerializedName

data class Job(
    val id: Int?,
    val organisation: Organisation?,
    @SerializedName("post_date") val postDate: String?,
    val title: String?,
    val link: String?,
    @SerializedName("employment_type") val employmentType: String?,
    val discipline: String?,
    val description: String?,
    val qualifications: String?,
    val requirements: String?
)