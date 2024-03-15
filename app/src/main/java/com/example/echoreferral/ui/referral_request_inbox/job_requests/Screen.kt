package com.example.echoreferral.ui.referral_request_inbox.job_requests

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.echoreferral.R
import com.example.echoreferral.data.repository.sharedPreferreneceManager.SharedPreferrenceManagerRepo
import com.example.echoreferral.ui.job.components.JobCard

@Composable
fun JobRequestsScreen(jobId:Int,modifier: Modifier= Modifier) {
    val requestViewModel : RequestViewModel = viewModel()
    val allRequestStatus = requestViewModel.requestsResponse.observeAsState()
    val sp = SharedPreferrenceManagerRepo(LocalContext.current)
    LaunchedEffect(key1 = Unit) {
        requestViewModel.getAllRequestsByJobId(token = sp.token, jobId = jobId)
    }
    Box(modifier = modifier
        .fillMaxWidth()
        .padding(10.dp)) {
//        CandidateCard(
//            profilePicture = R.drawable.profile_picture,
//            firstName = "John",
//            lastName = "Doe",
//            score = "85%"
//        )

        LazyColumn(content = {
            allRequestStatus.value?.data?.let {
                items(it) {
                    CandidateCard(profilePicture = R.drawable.profile_picture, firstName = "Jon", lastName = "don", score = it.score)
                }
            }
        })
    }
}

@Composable
fun CandidateCard(profilePicture: Int, firstName: String, lastName: String, score: Float?) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(painter = painterResource(id = profilePicture), contentDescription = "cand",modifier = Modifier
                .size(60.dp)
                .clip(CircleShape),)
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "$firstName $lastName",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Score: $score",
                )
            }
        }
    }
}