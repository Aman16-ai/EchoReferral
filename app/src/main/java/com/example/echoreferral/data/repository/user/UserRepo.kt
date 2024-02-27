package com.example.echoreferral.data.repository.user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.echoreferral.data.model.entities.UserProfile
import com.example.echoreferral.data.service.AuthService

class UserRepo : UserRepoI {

    private var _userProfile = MutableLiveData<UserProfile?>()
    override val userProfile: LiveData<UserProfile?>
        get() = _userProfile

    override suspend fun getUserDetails(token: String) {
        val result = AuthService
            .authServiceInstance
            .getUserDetails("Bearer $token")
            .body()
        _userProfile.postValue(result?.Response)
    }

}