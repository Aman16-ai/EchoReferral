package com.example.echoreferral.data.repository.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.echoreferral.data.model.payload.LoginFormPayload
import com.example.echoreferral.data.model.payload.RegisterationFormPayload
import com.example.echoreferral.data.model.response.LoginApiResponse
import com.example.echoreferral.data.model.response.RegisterationApiResponse
import com.example.echoreferral.data.service.AuthService
class AuthRepoImp : AuthRepo{


    private var _response : MutableLiveData<RegisterationApiResponse?> = MutableLiveData()
    val response: LiveData<RegisterationApiResponse?>
        get() = _response

    private var _loginResponse : MutableLiveData<LoginApiResponse?> = MutableLiveData()
    override val loginApiResponse : LiveData<LoginApiResponse?>
        get() = _loginResponse

    override suspend fun registerUser(body: RegisterationFormPayload) {
        val result = AuthService
            .authServiceInstance
            .registerUser(body)
            .body()
        _response.postValue(result)
    }

    override suspend fun loginUser(body: LoginFormPayload) {
        val result = AuthService
            .authServiceInstance
            .loginUser(body)
            .body()
        _loginResponse.postValue(result)
    }
}