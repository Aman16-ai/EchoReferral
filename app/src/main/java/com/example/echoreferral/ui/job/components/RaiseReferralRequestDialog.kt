package com.example.echoreferral.ui.job.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.echoreferral.data.model.payload.referral_request.ReferralRequestPayload
import com.example.echoreferral.data.repository.sharedPreferreneceManager.SharedPreferrenceManagerRepo
import com.example.echoreferral.ui.common.toast
import com.example.echoreferral.ui.job.jobDetail.JobDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RaiseReferralRequestDialog(jobId:Int,organisationId:Int,vm:JobDetailViewModel,onDismissRequest : ()->Unit) {
    val context = LocalContext.current
    val sp = SharedPreferrenceManagerRepo(LocalContext.current)
    var pitchStatus by remember {
        mutableStateOf("")
    }
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = "Raise Referral Request",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top=5.dp)
                )
                Text(
                    text = "*Note pitch get a ranking with other pitches.",
                    fontSize = 11.sp,
                    modifier = Modifier.padding(top=5.dp)
                )
                OutlinedTextField(value = pitchStatus, onValueChange = {pitchStatus = it},placeholder ={ Text(
                    text = ("Pitch")
                )},modifier = Modifier
                    .fillMaxWidth()
                    .height(270.dp)
                    .padding(10.dp))

                Row(modifier = Modifier.fillMaxWidth(), Arrangement.End) {
                    TextButton(onClick = { onDismissRequest() }) {
                        Text(text = "Cancel")
                    }
                    TextButton(onClick = {
                        val payload = ReferralRequestPayload(
                            job = jobId,
                            organisation = organisationId,
                            pitch = pitchStatus
                        )
//                        context.toast(sp.token.toString())
                        vm.createReferralRequest(payload,sp.token)
                    }) {
                        Text(text = "Create")
                    }
                }
            }
        }
    }
}