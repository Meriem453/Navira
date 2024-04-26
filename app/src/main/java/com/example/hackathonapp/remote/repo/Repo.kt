package com.example.hackathonapp.remote.repo

import com.example.hackathonapp.Domaine.Resource
import com.example.hackathonapp.model.Ship
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class Repo @Inject constructor(
    private val auth: FirebaseAuth,
    private val store: FirebaseFirestore
) {
    fun loginUser(
        email: String,
        password: String,
        result: (Resource<String>) -> Unit) {
        auth.signInWithEmailAndPassword("${email}@gmail.com",password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    result.invoke(Resource.Success("Success","Success"))
                }
            }.addOnFailureListener {
                result.invoke(Resource.Failed(it.localizedMessage))
            }
    }
    fun getAllChips( result: (List<Ship>) -> Unit) {
        store.collection("ships")
            .get()
            .addOnSuccessListener {
                val notes = arrayListOf<Ship>()
                for (document in it) {
                    val name = document.get("name").toString()
                    val id = document.get("id") as Long
                    notes.add(Ship(id = id,name))
                }
                result(
                    notes)
            }
            .addOnFailureListener {
                result(
                    emptyList()
                )
            }
    }
    fun emergency(ship:Ship){

    }

}