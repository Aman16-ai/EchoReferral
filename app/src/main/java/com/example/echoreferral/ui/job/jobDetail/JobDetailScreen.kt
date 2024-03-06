package com.example.echoreferral.ui.job.jobDetail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.echoreferral.R
import com.example.echoreferral.ui.common.MDivider
import com.example.echoreferral.utils.ApiState


@Composable
fun JobDetailScreen(id : Int,modifier: Modifier = Modifier) {
    val jobDetailViewModel : JobDetailViewModel = viewModel()
    val jobDetailStatus = jobDetailViewModel.jobResponse.observeAsState()
    LaunchedEffect(key1 = Unit) {
        jobDetailViewModel.getJob(id)
    }
    when(jobDetailStatus.value) {
        is ApiState.Success -> {
            Log.d("jd", "JobDetailScreen: ${jobDetailStatus.value?.data?.toString()}")
        }
        is ApiState.Loading -> {

        }
        is ApiState.Error -> {

        }
        else -> {

        }
    }
    Box(modifier = modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Column {
            AsyncImage(
                model = jobDetailStatus.value?.data?.organisation?.img,
                contentDescription = "org",
                modifier = modifier
                    .size(60.dp)
                    .border(width = 2.dp, color = Color(0xFFF0F2F5))
            )

            Text(
                text = jobDetailStatus.value?.data?.title?:"Software engineer",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = modifier
                    .padding(top=11.dp)
            )
            Text(
                text = jobDetailStatus.value?.data?.organisation?.name?:"Google",
                fontSize = 20.sp,
                modifier = modifier
                    .padding(top=11.dp)
            )
            Divider(
                color = Color(0xFFF0F2F5),
                modifier = modifier
                    .padding(top=10.dp)
            )
//            rowCard()
        }
    }
}

@Composable
fun rowCard(modifier: Modifier = Modifier) {
    Card() {
       Column {
           Text(text = "LOCATION", fontWeight = FontWeight.Light)
           Text(
               text = "Hyderabad",
               fontWeight = FontWeight.SemiBold,
               fontSize = 12.sp,
               modifier = modifier
                   .padding(top=5.dp)
           )
       }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun preview() {
    rowCard()
}