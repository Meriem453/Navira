package com.example.hackathonapp.remote.repo

import android.util.Log
import com.example.hackathonapp.Domaine.Resource
import com.example.hackathonapp.model.Ship
import com.example.hackathonapp.model.users
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import javax.inject.Inject

class Repo @Inject constructor(
    private val auth: FirebaseAuth,
    private val store: FirebaseFirestore,
    private val fcm:FirebaseMessaging
) {
    fun loginUser(
        email: String,
        password: String,
        result: (Resource<String>) -> Unit) {
        auth.signInWithEmailAndPassword("${email}@gmail.com",password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    result.invoke(Resource.Success("Success","Success"))
                        fcm.token.addOnCompleteListener(
                            OnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    // Get new FCM registration token

                                    val token = task.result
                                    val document = store.collection("users").document()
                                    document
                                        .set(users(token))
                                        .addOnSuccessListener {
                                            Log.d("heyy","heyy")
                                            result.invoke(
                                         Resource.Success("token","token")
                                            )
                                        }
                                        .addOnFailureListener {
                                            result.invoke(Resource.Failed("failed"))
                                        }

                                }



                            })

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