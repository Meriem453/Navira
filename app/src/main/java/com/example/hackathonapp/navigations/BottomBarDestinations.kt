package com.example.hackathonapp.navigations

import com.example.hackathonapp.R

interface BottomBarDestinations{
    val route :String
    val icon: Int
    val title : String
}

object Map: BottomBarDestinations {
    override val route = "Map"
    override val icon = R.drawable.map
    override val title = "Map"
}

object Emergency: BottomBarDestinations {
    override val route = "Emergency"
    override val icon = R.drawable.exclamation_triangle
    override val title = "Emergency"
}

object Login {
     val route = "Login"
     val title = "Login"
}

object Start {
    val route = "Start"
    val title = "Start"
}

object Notifications {
    val route = "Notifications"
    val title = "Notifications"
}



object Home: BottomBarDestinations {
    override val route = "Home"
    override val icon = R.drawable.home
    override val title = "Home"
}


