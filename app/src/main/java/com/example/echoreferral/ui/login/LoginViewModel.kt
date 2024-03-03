package com.example.echoreferral.ui.login

import android.app.Application
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.echoreferral.data.model.payload.LoginFormPayload
import com.example.echoreferral.data.model.response.LoginApiResponse
import com.example.echoreferral.data.repository.authentication.AuthRepo
import com.example.echoreferral.data.repository.authentication.AuthRepoImp
import com.example.echoreferral.data.repository.sharedPreferreneceManager.SharedPreferrenceManagerRepo
import com.example.echoreferral.data.repository.sharedPreferreneceManager.SharedPreferrenceManagerRepoI
import com.example.echoreferral.utils.ApiState
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel() {



    private val authRepo : AuthRepo = AuthRepoImp()
    private lateinit var sharedPreferrenceManagerRepo : SharedPreferrenceManagerRepoI
    val loginResponse : LiveData<ApiState<LoginApiResponse?>> = authRepo.loginApiResponse


    fun loginUser(payload : LoginFormPayload) {
        viewModelScope.launch {
            authRepo.loginUser(payload)
        }
    }

    fun setContext(context: Context) {
        sharedPreferrenceManagerRepo = SharedPreferrenceManagerRepo(context = context)
    }
    fun saveToken(token:String?) {
        sharedPreferrenceManagerRepo.saveToken(token = token)
    }

}