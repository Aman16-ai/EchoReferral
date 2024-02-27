package com.example.echoreferral.ui.registration

import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.echoreferral.data.model.payload.LoginFormPayload
import com.example.echoreferral.data.model.payload.RegisterationFormPayload
import com.example.echoreferral.data.repository.sharedPreferreneceManager.SharedPreferrenceManagerRepo
import com.example.echoreferral.ui.common.toast
import com.example.echoreferral.ui.login.LoginState
import com.example.echoreferral.ui.login.LoginViewModel


@Composable
fun LoginScreen(modifier: Modifier=Modifier,navController: NavController) {
    val loginViewModel : LoginViewModel = viewModel()
    loginViewModel.setContext(LocalContext.current)
    val sp = SharedPreferrenceManagerRepo(LocalContext.current)
    var responseState = loginViewModel.loginResponse.observeAsState()
    val uiState = remember{mutableStateOf(LoginState())}


    Log.d("loginUser", "loginscreen: ${responseState.toString()}")
    LaunchedEffect(key1 = responseState.value) {
        if(responseState.value?.status == 200) {
            val token = responseState.value?.Response?.access
            Log.d("authtoken", "LoginScreen: $token")
//            loginViewModel.saveToken(token)
            if (token != null) {
                sp.token = token
                navController.navigate("main")
            }
        }
    }
    fun createPayload(): LoginFormPayload {
        val payload = LoginFormPayload(
            username = uiState.value.username,
            password = uiState.value.password
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
            Text(text = "Login", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Text(
                text = "Login with your username and password",
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 5.dp)
            )
            LoginForm(uistate = uiState)
        }
        Column() {
            Text(text = "Don't have an account ? Register",
                modifier = Modifier
                    .padding(start = 5.dp, bottom = 7.dp)
                    .clickable { navController.navigate("register") }
            )
            Button(onClick = {
                Log.d("loginpay", "loginscreen: ${createPayload().toString()}")
                loginViewModel.loginUser(createPayload())
            },modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
            ) {
                Text(text = "Login")
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginForm(modifier: Modifier= Modifier, uistate:MutableState<LoginState>) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .padding(top = 30.dp)

    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Column(modifier = Modifier
                .fillMaxWidth()) {
                OutlinedTextField(value = uistate.value.username, onValueChange = {uistate.value = uistate.value.copy(username = it)},placeholder ={ Text(
                    text = ("Username")
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

