package com.example.echoreferral.data.repository.sharedPreferreneceManager

import android.content.Context
import androidx.lifecycle.LiveData

interface SharedPreferrenceManagerRepoI {
    val auth_token : LiveData<String?>
    fun getToken()
    fun saveToken(token:String?)
}