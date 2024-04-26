package com.example.hackathonapp.remote.repo

import android.app.Activity
import android.app.Application
import android.util.Log
import com.example.hackathonapp.Domaine.NotificationSender
import com.example.hackathonapp.Domaine.NotificationsService
import com.example.hackathonapp.Domaine.Resource
import com.example.hackathonapp.model.Notification
import com.example.hackathonapp.model.Quay
import com.example.hackathonapp.model.Ship
import com.example.hackathonapp.model.users
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import java.time.LocalDate
import java.util.Random
import javax.inject.Inject

class Repo @Inject constructor(
    private val auth: FirebaseAuth,
    private val store: FirebaseFirestore,
    private val fcm:FirebaseMessaging,
    private val notification:NotificationsService,
    private val app:Application
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

                                    val token = task.result
                                    val document = store.collection("users").document()
                                    document
                                        .set(users(password,token))
                                        .addOnSuccessListener {
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
    fun getAllShips( result: (Resource<List<Ship>>) -> Unit) {
        store.collection("ships")
            .get()
            .addOnSuccessListener {
                val ships = arrayListOf<Ship>()
                for (document in it) {
                    val name = document.get("name").toString()
                    val id = document.get("id") as Long
                    val state : String=document.get("id") as String
                    val priority : Int=document.get("priority") as Int
                    val size : Int=document.get("size") as Int
                    val type:String=document.get("type") as String
                    val ETA:String=document.get("ETA") as String
                    val origin:String=document.get("origin") as String
                    ships.add(Ship(id = id,name,state, priority, size, type, ETA, origin))
                }
                result(Resource.Success(ships,"Success")
                    )
            }
            .addOnFailureListener {
                result(
                    Resource.Failed("Failed")
                )
            }
    }
    fun declareEmergency(ship:Ship,activity: Activity,`class`:Class<*>, result: (Resource<List<Ship>>) -> Unit){
        //search for an empty quay
        getAllShips {
            if(it is Resource.Success){
                var found=false
                for (i in it.data!!){
                    if(i.size==ship.size && i.priority<ship.priority){
                        found=true
                        notification.showNotification(
                            "Ready to queue",
                            NotificationsService.NOTIFICATIONS_CHANNEL_ID,
                            Random().nextInt(100),
                            "Ship status updated",
                            `class`
                        )
                        //update ship status

                        val document = store.collection("ships").document(ship.id.toString())
                         ship.state="quay"

                        document
                            .set(ship)
                            .addOnSuccessListener {
                                    Log.d("success","suceess")
                            }
                            .addOnFailureListener {
                                Log.d("failed","failed")
                            }
                        val document2 = store.collection("ships").document(i.id.toString())
                        i.state="wait"

                        document2
                            .set(i)
                            .addOnSuccessListener {
                                Log.d("success","suceess")
                            }
                            .addOnFailureListener {
                                Log.d("failed","failed")
                            }
                        //update quays status
                        store.collection("quay")
                            .get()
                            .addOnSuccessListener {
                                val quays = arrayListOf<Quay>()
                                for (q in it) {
                                    val available = q.get("available") as Boolean
                                    val id = q.get("id") .toString()
                                    val occupiedBy  : String=q.get("occupiedBy") as String
                                    quays.add(Quay(id,available,occupiedBy))
                                }
                                val targeted=quays.filter { it.occupiedBy==i.id.toString() }[0]
                                targeted.occupiedBy=ship.id.toString()

                                val document4 = store.collection("quay").document(targeted.id)

                                document4
                                    .set(targeted)
                                    .addOnSuccessListener {
                                        Log.d("success","suceess")
                                    }
                                    .addOnFailureListener {
                                        Log.d("failed","failed")
                                    }


                            }
                            .addOnFailureListener { }
                        //send notification to desired ship to quit
                        store.collection("users")
                            .get()
                            .addOnSuccessListener {
                                val users= arrayListOf<users>()
                                for (u in it) {
                                    val token = u.get("token") as String
                                    val id = u.get("id") .toString()
                                    users.add(users(id, token))
                                }
                                val tuser=users.filter { it.id==i.id.toString() }
                                for (k in tuser){
                                    val notif=NotificationSender(
                                        k.token,"Ship status updated","You must give your place",app,activity
                                    )
                                    notif.SendNotifications()


                                }

                            }
                            .addOnFailureListener { }
                        //add notification
                        val document6 = store.collection("notifications").document()
                        document6
                            .set(Notification("Ship status updated",false,LocalDate.now().toString(),i.id.toString()))
                            .addOnSuccessListener {

                            }
                            .addOnFailureListener {

                            }
                        val document7 = store.collection("notifications").document()
                        document7
                            .set(Notification("Ship status updated",false,LocalDate.now().toString(),ship.id.toString()))
                            .addOnSuccessListener {}
                            .addOnFailureListener {}


                    }
                }
            }
        }
    }
    fun myShipInfo(){
        getAllShips {
            if (it is Resource.Success){

            }
        }
    }
    fun getAllNotif(){
        store.collection("notifications")
            .get()
            .addOnSuccessListener {
                val notif = arrayListOf<Notification>()
                for (document in it) {
                    val date = document.get("date").toString()
                    val message = document.get("message") as String
                    val ship : String=document.get("ship") as String
                    val state : Boolean=document.get("state") as Boolean

                    notif.add(Notification(message,state,date,ship))
                }
            }
            .addOnFailureListener {}
    }
    fun getAllQuay(){
        store.collection("quay")
            .get()
            .addOnSuccessListener {
                val quays = arrayListOf<Quay>()
                for (q in it) {
                    val available = q.get("available") as Boolean
                    val id = q.get("id") .toString()
                    val occupiedBy  : String=q.get("occupiedBy") as String
                    quays.add(Quay(id,available,occupiedBy))

                }
            }
            .addOnFailureListener {}
    }

}