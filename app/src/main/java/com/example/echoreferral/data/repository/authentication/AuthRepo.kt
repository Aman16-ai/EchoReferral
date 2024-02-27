package com.example.echoreferral.data.repository.authentication
import androidx.lifecycle.LiveData
import com.example.echoreferral.data.model.payload.LoginFormPayload
import com.example.echoreferral.data.model.payload.RegisterationFormPayload
import com.example.echoreferral.data.model.response.LoginApiResponse

interface AuthRepo {

    val loginApiResponse : LiveData<LoginApiResponse?>
    suspend fun registerUser(body:RegisterationFormPayload)
    suspend fun loginUser(body:LoginFormPayload)
}