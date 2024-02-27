package com.example.echoreferral.ui.registration

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.echoreferral.data.model.payload.RegisterationFormPayload


@Composable
fun RegistrationScreen(modifier: Modifier=Modifier,navController: NavController) {
    val registrationViewModel : RegistrationViewModel = viewModel()
    var responseState = registrationViewModel.registrationResponse.observeAsState()
    val uiState = remember{mutableStateOf(RegisterationState())}

    Log.d("createuser", "RegistrationScreen: ${responseState.toString()}")
    LaunchedEffect(key1 = responseState.value) {
        if(responseState.value?.status == 201) {
            navController.navigate("login")
        }
    }
    fun createPayload(): RegisterationFormPayload {
        val payload = RegisterationFormPayload(
            first_name = uiState.value.first_name,
            last_name = uiState.value.last_name,
            email =  uiState.value.email,
            password = uiState.value.password,
            username = uiState.value.username
        )
        return payload
    }
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        Column() {
            Text(text = "Register", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Text(
                text = "Create your account using username and password!",
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 5.dp)
            )
            Form(uistate = uiState)
        }
        Column() {
            Text(text = "Already have an account ? Login",
                modifier = Modifier
                    .padding(start = 5.dp, bottom = 7.dp)
                    .clickable { navController.navigate("login") }
            )
            Button(onClick = {
                registrationViewModel.registerUser(createPayload())
            },modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
            ) {
                Text(text = "Register")
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Form(modifier: Modifier= Modifier, uistate:MutableState<RegisterationState>) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .padding(top = 30.dp)

    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
                    OutlinedTextField(value = uistate.value.first_name, onValueChange = {uistate.value = uistate.value.copy(first_name = it)}, placeholder ={ Text(
                        text = ("First name")
                    )},modifier = Modifier
                        .weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedTextField(value = uistate.value.last_name, onValueChange = {uistate.value = uistate.value.copy(last_name = it)},placeholder ={ Text(
                        text = ("Last name")
                    )},modifier = Modifier
                        .weight(1f)
                    )
                }
                OutlinedTextField(value = uistate.value.username, onValueChange = {uistate.value = uistate.value.copy(username = it)},placeholder ={ Text(
                    text = ("Username")
                )},modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp))
                OutlinedTextField(value = uistate.value.email, onValueChange = {uistate.value = uistate.value.copy(email = it)},placeholder ={ Text(
                    text = ("Email")
                )},modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp))
                OutlinedTextField(value = uistate.value.password, onValueChange = {uistate.value = uistate.value.copy(password = it)},placeholder ={ Text(
                    text = ("Password")
                )},modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp))

            }
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun PreviewRegistrationScreen() {
//    RegistrationScreen(onChangeNavigation = {})
}

