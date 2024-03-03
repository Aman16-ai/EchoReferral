package com.example.echoreferral.data.repository.authentication
import androidx.lifecycle.LiveData
import com.example.echoreferral.data.model.payload.LoginFormPayload
import com.example.echoreferral.data.model.payload.RegisterationFormPayload
import com.example.echoreferral.data.model.response.LoginApiResponse
import com.example.echoreferral.utils.ApiState

interface AuthRepo {

    val loginApiResponse : LiveData<ApiState<LoginApiResponse?>>
    suspend fun registerUser(body:RegisterationFormPayload)
    suspend fun loginUser(body:LoginFormPayload)
}