package com.example.echoreferral.data.repository.user

import androidx.lifecycle.LiveData
import com.example.echoreferral.data.model.entities.UserProfile

interface UserRepoI {

    val userProfile:LiveData<UserProfile?>
    suspend fun getUserDetails(token:String)
}