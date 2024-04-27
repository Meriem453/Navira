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

object Home: BottomBarDestinations {
    override val route = "Home"
    override val icon = R.drawable.home
    override val title = "Home"
}


