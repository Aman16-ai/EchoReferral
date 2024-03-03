package com.example.echoreferral.ui.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.echoreferral.data.repository.sharedPreferreneceManager.SharedPreferrenceManagerRepo

@Composable
fun MainScreen(navController: NavController) {
//    val sp = SharedPreferrenceManagerRepo(LocalContext.current)
    Text(text = "This is the home screen welcome")

}