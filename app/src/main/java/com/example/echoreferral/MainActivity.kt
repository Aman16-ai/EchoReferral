package com.example.echoreferral

import android.annotation.SuppressLint
import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.colorspace.Rgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.echoreferral.data.repository.sharedPreferreneceManager.SharedPreferrenceManagerRepo
import com.example.echoreferral.ui.common.viewmodels.UserViewModel
import com.example.echoreferral.ui.job.JobScreen
import com.example.echoreferral.ui.main.MainScreen
import com.example.echoreferral.ui.profile.ProfileScreen
import com.example.echoreferral.ui.registration.LoginScreen

import com.example.echoreferral.ui.registration.RegistrationScreen
import com.example.echoreferral.ui.theme.EchoReferralTheme
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EchoReferralTheme {
               Surface {
                   val navController = rememberNavController()
                   val navBackStackEntry by navController.currentBackStackEntryAsState()
                   var navigationSelectedItem by remember {
                       mutableStateOf(0)
                   }
                   val onIndexChange:(i : Int) -> Unit = {i ->
                       navigationSelectedItem = i

                   }
                   Box(modifier = Modifier.fillMaxHeight()) {
                       Scaffold(modifier = Modifier.fillMaxSize(),bottomBar = {
                           val currentRoute = navBackStackEntry?.destination?.route
                           Log.d("route", "onCreate: $currentRoute")
                           if(currentRoute != "register" && currentRoute != "login") {
                                BottomNavigationGraph(navController = navController,navigationSelectedItem,onIndexChange)
                            }
                       }) {
                           App(navController)
                       }
                   }
               }
            }
        }
    }
}

@Composable
fun App(navController: NavHostController ) {
    val sp = SharedPreferrenceManagerRepo(LocalContext.current)
    val userViewModel : UserViewModel = viewModel()
    val userProfileState = userViewModel.userProfile.observeAsState()
    LaunchedEffect(key1 = Unit) {
        userViewModel.getUserProfileDetails(sp.token)
    }

//    LaunchedEffect(key1 = userProfileState.value) {
//        Log.d("userprofile", "App: ${userProfileState.value.toString()}")
//    }
    NavHost(navController = navController, startDestination = "register") {
        composable("register") {
            if(sp.token.isNotEmpty() && userProfileState.value != null) {
                LaunchedEffect(key1 = Unit) {
                    navController.navigate("main") {
                        popUpTo("login") {
                            inclusive=true
                        }

                    }
                }
            }
             RegistrationScreen(navController=navController)
        }
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("main") {
            MainScreen(navController = navController)
        }
        composable("profile") {
            ProfileScreen()
        }
        composable("job") {
            JobScreen()
        }
    }
}


data class BottomNavigationItem(
    val label : String = "",
    val icon : ImageVector = Icons.Filled.Home,
    val outLinedIcon : ImageVector =Icons.Outlined.Home,
    val route : String = ""
) {

    //function to get the list of bottomNavigationItems
    @Composable
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Home",
                icon = Icons.Filled.Home,
                outLinedIcon = Icons.Outlined.Home,
                route = "main"
            ),
            BottomNavigationItem(
                label = "Inbox",
                icon = Icons.Filled.Send,
                outLinedIcon = Icons.Outlined.Send,
                route = "inbox"
            ),
            BottomNavigationItem(
                label = "Jobs",
                icon = ImageVector.vectorResource(id = R.drawable.baseline_work_24),
                outLinedIcon = ImageVector.vectorResource(id = R.drawable.outline_work_outline_24),
                route = "job"
            ),
            BottomNavigationItem(
                label = "Profile",
                icon = Icons.Filled.AccountCircle,
                outLinedIcon = Icons.Outlined.AccountCircle,
                route = "profile"
            ),
        )
    }
}
@Composable
fun BottomNavigationGraph(navController: NavController,navigationSelectedItem : Int,onIndexChange : (i : Int) -> Unit) {

//    NavigationBar {
//        BottomNavigationItem().bottomNavigationItems()
//            .forEachIndexed { index, navigationItem ->
//                NavigationBarItem(
//                    selected = index == navigationSelectedItem,
//                    label = {
//                            Text(navigationItem.label)
//                    },
//                    icon = {
//                        Icon(navigationItem.icon, contentDescription = navigationItem.label)
//                    },
//                    onClick = {
//
////                        navigationSelectedItem = index
//                        onIndexChange(index)
//                        navController.navigate(navigationItem.route) {
//                            popUpTo(navController.graph.findStartDestination().id) {
//                                saveState = true
//                            }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
//
//                    })
//
//            }
//
//    }
    Column() {
        Divider (
            color = Color(0xFFF0F2F5),
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
        )
        Row(

            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp)
                .background(Color.Transparent)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically

        ) {
            BottomNavigationItem().bottomNavigationItems()
                .forEachIndexed { index, navigationItem ->
                    CustomsBottomNavItems(
                        selected = index == navigationSelectedItem,
                        label = navigationItem.label,
                        filledIcon = {
                            Icon(
                                navigationItem.icon,
                                contentDescription = navigationItem.label,
                                modifier = Modifier.size(25.dp),
                            )
                        },
                        outLinedIcon = {
                            Icon(
                                navigationItem.outLinedIcon,
                                contentDescription = navigationItem.label,
                                modifier = Modifier
                                    .size(25.dp),
                                tint = Color.Gray
                            )
                        },
                        onClick ={

//                        navigationSelectedItem = index
                            onIndexChange(index)
                            navController.navigate(navigationItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }

                        })

                }
        }
    }
}

@Composable
fun RowScope.CustomsBottomNavItems(
    selected:Boolean,
    label:String,
    filledIcon:@Composable ()->Unit,
    outLinedIcon : @Composable ()-> Unit,
    onClick : ()->Unit
) {

    val icon =
        if (selected) filledIcon else outLinedIcon
    val textColor =
        if (selected) Color.Black else Color.Gray
    Box(
        modifier = Modifier
            .height(48.dp)
            .clickable { onClick() }

    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            icon()
            Text(text = label, color = textColor, fontSize = 13.sp)
        }
    }
}
