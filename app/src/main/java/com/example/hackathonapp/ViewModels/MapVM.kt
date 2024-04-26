package com.example.hackathonapp.ViewModels

import androidx.lifecycle.ViewModel
import com.example.hackathonapp.remote.repo.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapVM @Inject constructor(
    repo: Repo
):ViewModel(){

}