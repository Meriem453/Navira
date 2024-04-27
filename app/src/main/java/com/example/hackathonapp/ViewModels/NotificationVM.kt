package com.example.hackathonapp.ViewModels

import androidx.lifecycle.ViewModel
import com.example.hackathonapp.Domaine.Resource
import com.example.hackathonapp.model.Notification
import com.example.hackathonapp.remote.repo.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationVM @Inject constructor(
    private val repo: Repo
):ViewModel(){

    fun getAllNotif(notif:(List<Notification>)->Unit){
        repo.getAllNotif {
            if(it is Resource.Success)
                notif(it.data!!)

        }
    }


}