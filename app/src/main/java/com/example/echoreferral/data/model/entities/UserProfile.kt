package com.example.echoreferral.data.model.entities

data class UserProfile(
    val id:Int?,
    val user:User?,
    val headline:String?,
    val profile_completed:Boolean?,
    val profile_progress:Double?
) {
    data class User(
        val id:Int?,
        val first_name:String?,
        val last_name:String?,
        val username:String?,
        val email:String?,
    )
}