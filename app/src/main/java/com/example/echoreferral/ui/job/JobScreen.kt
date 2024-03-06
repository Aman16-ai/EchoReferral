package com.example.echoreferral.ui.job


import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.echoreferral.ui.common.viewmodels.TopNavBarViewModel
import com.example.echoreferral.ui.job.components.JobCard
import com.example.echoreferral.utils.ApiState

@Composable
fun JobScreen(topNavBarViewModel: TopNavBarViewModel,navController: NavController,modifier: Modifier = Modifier) {
    val jobViewModel : JobViewModel = viewModel()
    val jobResponse = jobViewModel.jobResponse.observeAsState()
    LaunchedEffect(key1 = Unit) {
        jobViewModel.getAllJobs()
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
                    JobCard(topNavBarViewModel=topNavBarViewModel,navController=navController, job = it)
                }
            }
        })
    }
}