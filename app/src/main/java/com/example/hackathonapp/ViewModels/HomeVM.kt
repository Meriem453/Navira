package com.example.hackathonapp.ViewModels

import androidx.lifecycle.ViewModel
import com.example.hackathonapp.Domaine.Resource
import com.example.hackathonapp.model.Ship
import com.example.hackathonapp.remote.repo.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
   private val repo:Repo
):ViewModel(){
    fun getALlShips(ships:(List<Ship>)->Unit){
        repo.getAllShips {
            if(it is Resource.Success)
                ships(it.data!!)
        }
    }


}