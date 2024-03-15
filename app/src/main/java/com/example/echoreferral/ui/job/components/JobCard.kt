package com.example.echoreferral.ui.job.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.echoreferral.R
import com.example.echoreferral.data.model.entities.Job
import com.example.echoreferral.ui.common.MDivider
import com.example.echoreferral.ui.common.viewmodels.TopNavBarViewModel

@Composable
fun JobCard(topNavBarViewModel: TopNavBarViewModel,navController: NavController,job : Job,modifier:Modifier = Modifier,requestRouteEnable:Boolean=false) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            topNavBarViewModel.setOrganisation(job.organisation)
            if (requestRouteEnable) {
                navController.navigate("allRequests/${job.id}")
            }
            else {
                navController.navigate("jobs/${job.id}")
            }
        }) {
        Row(modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            ) {
//            Image(painter = painterResource(id = R.drawable.google_logo), contentDescription = "logo", modifier = modifier.size(45.dp).border(2.dp, color = Color(0xFFF0F2F5)))
            AsyncImage(model = job.organisation?.img, contentDescription = "company",modifier= modifier.size(45.dp))
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp)
                ) {
                Text(
                    text = job.title?:"Title",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp
                )
                Text(
                    text = job.organisation?.name ?: "Company",
                    modifier = modifier.padding(top = 6.dp),
                    fontSize = 14.sp
                )
                Text(
                    text = "1-3 Years | In Office | Hyderabad",
                    fontWeight = FontWeight.Light, fontSize = 12.sp,
                    modifier = modifier.padding(top = 7.dp),

                )

//                Text(
//                    text = "3 hours ago",
//                    modifier = modifier.padding(top = 4.dp),
//                    fontSize = 16.sp,
//                    color = Color.Green
//                )

                Text(
                    text = "As per industry standards",
                    modifier = modifier.padding(top = 7.dp, bottom = 8.dp),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )

                MDivider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCard() {
//    JobCard()
}