package com.example.hackathonapp.model

data class Ship(
    val id: Long,
    val name: String="",
    var state : String="",
    val priority : Int=0,
    val size : Int=0,
    val type:String="",
    val ETA:String="",
    val origin:String=""
)