package com.example.echoreferral

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.echoreferral.data.repository.sharedPreferreneceManager.SharedPreferrenceManagerRepo
import com.example.echoreferral.ui.common.viewmodels.UserViewModel
import com.example.echoreferral.ui.main.MainScreen
import com.example.echoreferral.ui.registration.LoginScreen

import com.example.echoreferral.ui.registration.RegistrationScreen
import com.example.echoreferral.ui.theme.EchoReferralTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EchoReferralTheme {
               Surface() {
                   Box(modifier = Modifier.fillMaxHeight()) {
                       App()
                   }
               }
            }
        }
    }
}

@Composable
fun App() {
    val sp = SharedPreferrenceManagerRepo(LocalContext.current)
    val userViewModel : UserViewModel = viewModel()
    val userProfileState = userViewModel.userProfile.observeAsState()
    LaunchedEffect(key1 = Unit) {
        userViewModel.getUserProfileDetails(sp.token)
    }

    LaunchedEffect(key1 = userProfileState.value) {
        Log.d("userprofile", "App: ${userProfileState.value.toString()}")
    }
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "register") {
        composable("register") {
            if(sp.token.isNotEmpty() && userProfileState.value != null) {
                navController.navigate("main") {
                    popUpTo("login") {
                        inclusive=true
                    }

                }
            }
            else RegistrationScreen(navController=navController)
        }
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("main") {
            MainScreen(navController = navController)
        }
    }
}