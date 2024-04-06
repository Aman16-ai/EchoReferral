package com.example.echoreferral.ui.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.echoreferral.data.repository.sharedPreferreneceManager.SharedPreferrenceManagerRepo
import com.example.echoreferral.ui.common.TextWithHoriLine
import com.example.echoreferral.ui.common.viewmodels.TopNavBarViewModel
import com.example.echoreferral.ui.job.JobViewModel
import com.example.echoreferral.ui.job.components.JobCard
import com.example.echoreferral.utils.ApiState

@Composable
fun HomeScreen(navController: NavController,topNavBarViewModel: TopNavBarViewModel) {
    val jobViewModel : JobViewModel = viewModel()
    val jobResponse = jobViewModel.jobResponse.observeAsState()

    val homeVm : HomeScreenViewModel = viewModel()
    val requestsStatusResponse = homeVm.requestStatus.observeAsState()

    var sendStatusCount by remember{
        mutableStateOf(0)
    }
    var receivedStatusCount by remember{
        mutableStateOf(0)
    }
    var acceptedStatusCount by remember{
        mutableStateOf(0)
    }

    var rejectedStatusCount by remember{
        mutableStateOf(0)
    }
    val sp = SharedPreferrenceManagerRepo(LocalContext.current)

    LaunchedEffect(key1 = Unit) {
        jobViewModel.getRecentJobs()
        homeVm.getUserRequestsStatus(sp.token)
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

    when (requestsStatusResponse.value) {
        is ApiState.Success -> {
            // todo : code refactoring this logic must be in viewmodel or repository
            for(s in requestsStatusResponse.value?.data!!) {
                if(s.status === "send") {
                    sendStatusCount = s.count?: 0
                }
                else if(s.status === "received") {
                    receivedStatusCount = s.count?: 0
                }
                else if(s.status === "accepted") {
                    acceptedStatusCount = s.count ?: 0
                }
                else {
                    acceptedStatusCount = s.count ?: 0
                }
            }
        }
        is ApiState.Loading -> {

        }
        is ApiState.Error -> {
            Log.d("all rs", "home: ${requestsStatusResponse.value.toString()}")
        }
        else -> {

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TextWithHoriLine(text = "Requests Status", modifier = Modifier.padding(start=14.dp,top=10.dp))
        DashboardScreen(
            sendCount = sendStatusCount,
            receivedCount = receivedStatusCount,
            acceptedCount = acceptedStatusCount,
            rejectedCount = rejectedStatusCount
        )
        TextWithHoriLine(text = "Job Alert", modifier = Modifier.padding(start=14.dp,top=10.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            LazyRow(content = {
                jobResponse.value?.data?.let {
                    items(it) {
                        JobCard(topNavBarViewModel=topNavBarViewModel,navController=navController, job = it)
                    }
                }
            })
        }
        TextWithHoriLine(text = "Recent Requests", modifier = Modifier.padding(start=14.dp,top=10.dp))
    }
}

@Composable
fun DashboardScreen(
    sendCount: Int,
    receivedCount: Int,
    acceptedCount: Int,
    rejectedCount: Int
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(4.dp)
    ) {
        item {
            DashboardItem("Send", sendCount, Icons.Default.Send, Color(0xFF328CF0))
        }
        item {
            DashboardItem("Received", receivedCount, Icons.Default.Create, Color(0xFFE0A80C))
        }
        item {
            DashboardItem("Accepted", acceptedCount, Icons.Default.Done, Color(0xFF1CAA50))
        }
        item {
            DashboardItem("Rejected", rejectedCount, Icons.Default.Close, Color(0xFFDE2D2D))
        }
    }
}

@Composable
fun DashboardItem(label: String, count: Int,icon:ImageVector, color: Color) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(90.dp),
        colors = CardDefaults.cardColors(
            containerColor = color,
            contentColor = Color.Black
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        shape = RoundedCornerShape(7.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(icon, contentDescription = null)
//            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.padding(top=5.dp)) {
                Text(text = label)
                Text(text = count.toString(), modifier = Modifier.padding(start=5.dp))
            }
        }
    }
}


