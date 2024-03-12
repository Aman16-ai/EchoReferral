package com.example.echoreferral.ui.job.jobDetail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.echoreferral.R
import com.example.echoreferral.ui.common.MDivider
import com.example.echoreferral.ui.job.components.RaiseReferralRequestDialog
import com.example.echoreferral.utils.ApiState


@Composable
fun JobDetailScreen(id : Int,modifier: Modifier = Modifier) {
    val jobDetailViewModel : JobDetailViewModel = viewModel()
    val jobDetailStatus = jobDetailViewModel.jobResponse.observeAsState()
    var loading by remember{
        mutableStateOf(false)
    }
    var openReferralRequestModal by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = Unit) {
        jobDetailViewModel.getJob(id)
    }
    when(jobDetailStatus.value) {
        is ApiState.Success -> {
            loading = false
            Log.d("jd", "JobDetailScreen: ${jobDetailStatus.value?.data?.toString()}")
        }
        is ApiState.Loading -> {
            loading = true
        }
        is ApiState.Error -> {
            loading = false
        }
        else -> {

        }
    }
    Box(modifier = modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        if(!loading) {
            if(openReferralRequestModal) {
                RaiseReferralRequestDialog {
                    openReferralRequestModal = false
                }
            }
            Column(verticalArrangement = Arrangement.SpaceBetween) {
                Column(modifier = Modifier
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
                    .weight(1f, false)) {
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
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier= modifier
                            .fillMaxWidth()
                            .height(120.dp)

                    ) {
                        item {
                            rowCard(key = "LOCATION", value = "Hyderabad")
                        }
                        item {
                            rowCard(key = "SALARY", value = "45L - 66L")

                        }
                        item {
                            rowCard(key = "MINIMUM EXPERIENCE", value = "5+ Years")
                        }
                        item {
                            rowCard(key = "TYPE", value = "Remote")
                        }
                    }
                    Divider(
                        color = Color(0xFFF0F2F5),
                        modifier = modifier
                            .padding(top=10.dp)
                    )

                    Text(text = "Job Description", modifier = modifier.padding(top=12.dp), fontSize = 17.sp, fontWeight = FontWeight.SemiBold, color=Color.Gray)

                    Text(
                        text = jobDetailStatus.value?.data?.description ?: "",
                        modifier = modifier
                            .padding(top=8.dp),

                        )

                    Text(text = "Qualifications", modifier = modifier.padding(top=12.dp), fontSize = 17.sp, fontWeight = FontWeight.SemiBold, color=Color.Gray)

                    Text(
                        text = jobDetailStatus.value?.data?.qualifications ?: "",
                        modifier = modifier
                            .padding(top=8.dp),

                        )
                    Text(text = "Requirements", modifier = modifier.padding(top=12.dp), fontSize = 17.sp, fontWeight = FontWeight.SemiBold, color=Color.Gray)

                    Text(
                        text = jobDetailStatus.value?.data?.requirements ?: "",
                        modifier = modifier
                            .padding(top=8.dp),

                        )
                }
                Column {
                    Divider(
                        color = Color(0xFFF0F2F5),
                        modifier = modifier
                            .padding(top=10.dp)
                    )
                    Button(
                        onClick = { openReferralRequestModal = true },
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                            .height(48.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = "Raise Request")
                    }
                }
            }
        }
        else {
            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.width(40.dp),
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
        }
    }


}

@Composable
fun rowCard(key:String,value:String,modifier: Modifier = Modifier) {
    Column(modifier= modifier
        .fillMaxWidth()
        .padding(top = 20.dp)) {
        Text(
            text = key,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            color = Color.Gray
        )
        Text(
            text = value,
            fontSize = 15.sp,
            fontWeight= FontWeight.Bold,
            modifier = modifier
                .padding(top=8.dp)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun preview() {
//    rowCard()
}