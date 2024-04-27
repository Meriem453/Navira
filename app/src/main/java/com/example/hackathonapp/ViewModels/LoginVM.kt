package com.example.hackathonapp.ViewModels

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.hackathonapp.Domaine.Resource
import com.example.hackathonapp.remote.repo.Repo
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(
    private val repo:Repo
):ViewModel() {
fun test(activity: Activity){
repo.declareEmergency(activity){
    Log.d("VM",it.message!!)
}
}

fun login(shipName:String,id:String,result:(Resource<String>)->Unit){
    repo.loginUser(shipName,id,result)
}
}