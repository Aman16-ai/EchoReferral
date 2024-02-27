package com.example.echoreferral.ui.common.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.echoreferral.data.repository.user.UserRepo
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {

    private val userRepo = UserRepo()
    val userProfile = userRepo.userProfile

    fun getUserProfileDetails(token:String) {
        viewModelScope.launch {
            userRepo.getUserDetails(token)
        }
    }
}