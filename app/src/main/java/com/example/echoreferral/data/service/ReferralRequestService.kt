package com.example.echoreferral.data.service

import com.example.echoreferral.data.service.api.ReferralRequestApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ReferralRequestService {

    val referralRequestInstance : ReferralRequestApi
    init {
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(BaseApi.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        referralRequestInstance = retrofit.create(ReferralRequestApi::class.java)
    }
}