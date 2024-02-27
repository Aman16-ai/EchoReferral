package com.example.echoreferral.data.service.api

import com.example.echoreferral.data.model.payload.LoginFormPayload
import retrofit2.http.Body
import retrofit2.http.POST
import com.example.echoreferral.data.model.payload.RegisterationFormPayload
import com.example.echoreferral.data.model.response.GetUserDetailsApiResponse
import com.example.echoreferral.data.model.response.LoginApiResponse
import com.example.echoreferral.data.model.response.RegisterationApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface AuthApi {

    @POST("/api/user/")
    suspend fun registerUser(@Body payload: RegisterationFormPayload) : Response<RegisterationApiResponse>

    @POST("/api/user/login/")
    suspend fun loginUser(@Body payload: LoginFormPayload) : Response<LoginApiResponse>

    @GET("/api/user/getUserDetails/")
    suspend fun getUserDetails(@Header("Authorization") token:String) : Response<GetUserDetailsApiResponse>
}