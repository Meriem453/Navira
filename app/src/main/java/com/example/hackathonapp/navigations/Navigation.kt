package com.example.hackathonapp.navigations

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.hackathonapp.screens.HomeScreen
import com.example.hackathonapp.screens.MapScreen


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Navigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = Home.route){
        composable(route = Home.route){
            HomeScreen(navController)
        }

        composable(route = Map.route){
            MapScreen(navController)
        }
    }
}


