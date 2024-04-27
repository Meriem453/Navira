package com.example.hackathonapp.navigations

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hackathonapp.MainScreen
import com.example.hackathonapp.model.Notification

import com.example.hackathonapp.screens.HomeScreen
import com.example.hackathonapp.screens.LoginScreen
import com.example.hackathonapp.screens.MapScreen
import com.example.hackathonapp.screens.NotificationScreen
import com.example.hackathonapp.screens.gettingstartedScreen


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Navigation(navController: NavHostController){
    val sampleNotifications = listOf(
        Notification("Ready to queue", true, "2024-04-26", "123456"),
        Notification("Queue full, please wait for availability.", false, "2024-04-25","123456")
    )

    NavHost(navController = navController, startDestination = Start.route){
        composable(route = Home.route){
            HomeScreen(navController)
        }

        composable(route = Login.route){
            LoginScreen(navController)
        }

        composable(route = Notifications.route){
            NotificationScreen(navController, sampleNotifications)
        }

        composable(route = Start.route){
            gettingstartedScreen(navController)
        }

        composable(route = Map.route){
            MapScreen(navController)
        }
    }
}


