package com.example.hackathonapp.model

import androidx.annotation.DrawableRes

data class Page(
    val title: String,
    val description: String,

)

val pages = listOf(
    Page(
        title = "Welcome Aboard!",
        description = "Explore the high seas of opportunity with our Cargo Ship Position Management app."
    ),

    Page(
        title = "Navigate Seamlessly",
        description = "Effortlessly track and manage vessel positions with our user-friendly interface."
    ),

    Page(
        title = "Get Started",
        description = "Let's set sail! Begin your voyage by creating your account and setting up your fleet."
    )
)