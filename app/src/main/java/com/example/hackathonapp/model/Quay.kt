package com.example.hackathonapp.model

data class Quay(
    val id: Int,
    val available: Boolean,
    val occupiedBy: Int? // Ship ID or null if empty
)

