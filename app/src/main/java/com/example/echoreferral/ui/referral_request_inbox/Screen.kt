package com.example.echoreferral.ui.referral_request_inbox

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.echoreferral.ui.common.viewmodels.TopNavBarViewModel
import com.example.echoreferral.ui.common.viewmodels.UserViewModel
import com.example.echoreferral.ui.job.JobViewModel
import com.example.echoreferral.ui.job.components.JobCard
import com.example.echoreferral.utils.ApiState

@Composable
fun requestInboxScreen(topBarVm : TopNavBarViewModel,userVm : UserViewModel,navController: NavController, modifier: Modifier = Modifier) {
    val jobViewModel : JobViewModel = viewModel()
    val jobResponse = jobViewModel.jobResponse.observeAsState()
    val userProfileState = userVm.userProfile.observeAsState()
    LaunchedEffect(key1 = userProfileState.value) {
        userProfileState.value?.let { jobViewModel.getAllJobsOfCurrOrgansiationofUser(userProfile = it) }
    }
    when (jobResponse.value) {
        is ApiState.Success -> {
            Log.d("all jobs", "JobScreen: ${jobResponse.value.toString()}")
        }
        is ApiState.Loading -> {

        }
        is ApiState.Error -> {
            Log.d("all jobs", "JobScreen: ${jobResponse.value.toString()}")
        }
        else -> {

        }
    }
    Box(modifier = modifier.fillMaxWidth()) {
        LazyColumn(content = {
            jobResponse.value?.data?.let {
                items(it) {
                    JobCard(topNavBarViewModel=topBarVm,navController=navController, job = it, requestRouteEnable = true)
                }
            }
        })
    }
}