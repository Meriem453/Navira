package com.example.hackathonapp.model

import com.example.hackathonapp.R

data class Notification(
    val message : String,
    val state : Boolean,
    val date : String,
    val ship: String
) {
}

val sampleNotifications = listOf(
    Notification("Ready to queue", true, "2024-04-26", "123456"),
    Notification("Queue full, please wait for availability.", false, "2024-04-25","123456")
)