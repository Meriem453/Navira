package com.example.hackathonapp

import android.content.Context
import android.net.Uri
import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.hackathonapp.ViewModels.LoginVM
import com.example.hackathonapp.navigations.Emergency
import com.example.hackathonapp.navigations.Home
import com.example.hackathonapp.navigations.Map
import com.example.hackathonapp.navigations.Navigation
import com.example.hackathonapp.screens.HomeScreen
import com.example.hackathonapp.screens.LoginScreen
import com.example.hackathonapp.screens.gettingstartedScreen
import com.example.hackathonapp.ui.theme.HackathonAppTheme
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout.RESIZE_MODE_ZOOM
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.accompanist.navigation.material.BottomSheetNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HackathonAppTheme {
                val navController = rememberNavController()
                val c = LocalContext.current
                // A surface container using the 'background' color from the theme
                //MainScreen(this)
                // gettingstartedScreen(getVideoUri())
                LoginScreen(navController = navController )
            }
        }
    }

    private fun getVideoUri(): Uri {
        val rawId = resources.getIdentifier("ships", "raw", packageName)
        val videoUri = "android.resource://$packageName/$rawId"
        return Uri.parse(videoUri)
    }
}


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(activity: Activity) {
    val vm = hiltViewModel<LoginVM>()
    val c = LocalContext.current
    vm.login("meriem", "123456") {
        Log.d("login", "login")
    }
    vm.test(activity)


    val navController = rememberNavController()

    val destinationsList = listOf(Home, Emergency, Map)
    var selectedIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    Scaffold(
        bottomBar = {

            NavigationBar(modifier = Modifier.height(62.dp)) {
                destinationsList.forEachIndexed { index, screen ->
                    if (index == 1) {
                        NavigationBarItem(
                            icon = {

                                Icon(
                                    painter = painterResource(id = screen.icon),
                                    contentDescription = "icon",
                                    modifier = Modifier
                                        .size(55.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xff002C70))
                                        .padding(13.dp),
                                    tint = Color.White
                                )


                            },
                            selected = selectedIndex == index,
                            onClick = {
                                selectedIndex = index

                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color(0xff002C70),
                                unselectedIconColor = Color(0xff002C70)

                            )

                        )
                    } else {
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painter = painterResource(id = screen.icon),
                                    contentDescription = "icon"
                                )
                            },
                            selected = selectedIndex == index,
                            onClick = {
                                selectedIndex = index
                                navController.navigate(destinationsList[selectedIndex].route) {
                                    popUpTo(Home.route)
                                    launchSingleTop = true
                                }
                            })
                    }
                }
            }
        }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Navigation(navController = navController)
        }
    }
}

@Composable
fun bottombar() {

    val navController = rememberNavController()

    val destinationsList = listOf(Home, Emergency, Map)
    var selectedIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar(modifier = Modifier.height(60.dp)) {
        destinationsList.forEachIndexed { index, screen ->
            if (index == 1) {
                NavigationBarItem(
                    icon = {

                        Icon(
                            painter = painterResource(id = screen.icon),
                            contentDescription = "icon",
                            modifier = Modifier
                                .size(55.dp)
                                .clip(CircleShape)
                                .background(Color(0xff002C70))
                                .padding(13.dp),
                            tint = Color.White
                        )


                    },
                    selected = selectedIndex == index,
                    onClick = {
                        selectedIndex = index
                        navController.navigate(destinationsList[selectedIndex].route) {
                            popUpTo(Home.route)
                            launchSingleTop = true
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xff002C70),
                        unselectedIconColor = Color(0xff002C70)

                    )

                )
            } else {
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = screen.icon),
                            contentDescription = "icon"
                        )
                    },
                    selected = selectedIndex == index,
                    onClick = {
                        selectedIndex = index
                        navController.navigate(destinationsList[selectedIndex].route) {
                            popUpTo(Home.route)
                            launchSingleTop = true
                        }
                    })
            }
        }
    }

}

@Preview
@Composable
private fun bottombarprev() {
    bottombar()
}
