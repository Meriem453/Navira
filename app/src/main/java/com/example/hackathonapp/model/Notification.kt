package com.example.hackathonapp.model

import com.example.hackathonapp.R

data class Notification(
    val message : String,
    val iconResId : Int,
    val date : String,
    val ship:String
) {
}

val sampleNotifications = listOf(
    Notification("Ready to queue", R.drawable.ic_notification_success, "2024-04-26"),
    Notification("Queue full, please wait for availability.", R.drawable.ic_notification_warning, "2024-04-25")
)