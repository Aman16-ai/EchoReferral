package com.example.echoreferral.data.repository.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.echoreferral.data.model.payload.LoginFormPayload
import com.example.echoreferral.data.model.payload.RegisterationFormPayload
import com.example.echoreferral.data.model.response.LoginApiResponse
import com.example.echoreferral.data.model.response.RegisterationApiResponse
import com.example.echoreferral.data.service.AuthService
import com.example.echoreferral.utils.ApiState
import java.lang.Error

class AuthRepoImp : AuthRepo{


    private var _response : MutableLiveData<RegisterationApiResponse?> = MutableLiveData()
    val response: LiveData<RegisterationApiResponse?>
        get() = _response

    private var _loginResponse : MutableLiveData<ApiState<LoginApiResponse?>> = MutableLiveData()
    override val loginApiResponse : LiveData<ApiState<LoginApiResponse?>>
        get() = _loginResponse

    override suspend fun registerUser(body: RegisterationFormPayload) {
        val result = AuthService
            .authServiceInstance
            .registerUser(body)
            .body()
        _response.postValue(result)
    }

    override suspend fun loginUser(body: LoginFormPayload) {
        try {
            _loginResponse.postValue(ApiState.Loading())
            val result = AuthService
                .authServiceInstance
                .loginUser(body)
                .body()
            _loginResponse.postValue(ApiState.Success(data = result))
        }
        catch (_:Error) {
            _loginResponse.postValue(ApiState.Error(message = "Failed to login"))
        }
    }
}