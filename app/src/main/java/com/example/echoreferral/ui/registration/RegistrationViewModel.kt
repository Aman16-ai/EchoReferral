package com.example.echoreferral.ui.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.echoreferral.data.model.payload.RegisterationFormPayload
import com.example.echoreferral.data.model.response.RegisterationApiResponse
import com.example.echoreferral.data.repository.authentication.AuthRepoImp
import kotlinx.coroutines.launch

class RegistrationViewModel : ViewModel() {
    private val registrationRepo = AuthRepoImp()
    val registrationResponse:LiveData<RegisterationApiResponse?> = registrationRepo.response

    fun registerUser(body:RegisterationFormPayload) {
        viewModelScope.launch {
            registrationRepo.registerUser(body)
        }
    }


}