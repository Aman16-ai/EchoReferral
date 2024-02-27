package com.example.echoreferral.data.model.payload

data class RegisterationFormPayload(
    val first_name:String?,
    val last_name:String?,
    val email:String?,
    val password:String?,
    val username:String?
)