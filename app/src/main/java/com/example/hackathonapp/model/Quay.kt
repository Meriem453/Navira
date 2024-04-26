package com.example.hackathonapp.model

data class Quay(
    val id: String,
    val available: Boolean,
    var occupiedBy: String? // Ship ID or null if empty
)

