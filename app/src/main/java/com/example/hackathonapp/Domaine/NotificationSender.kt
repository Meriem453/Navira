package com.example.hackathonapp.Domaine;

import android.app.Activity
import android.content.Context
import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.hackathonapp.R
import org.json.JSONException
import org.json.JSONObject

class NotificationSender( var userFcmToken: String,
                          var title: String,
                          var body: String,
                          var mContext: Context,
                          var mActivity: Activity) {



    private var requestQueue: RequestQueue? = null
    private val postUrl = "https://fcm.googleapis.com/fcm/send"
    private val fcmServerKey =
        "AAAACFYjkcs:APA91bFWMD-h-Yz2HTEnkLVbdqxWIUjNv1DtzPxXoglgcoboJk__Rx25R6TgYicHsWQuROFjGWaCcnlpEYkpl0jk5qaOOoeLgx9TZNXfrI2H-g5eDC0Cev7H6hGkVXvg80k7i6SU44pH"



    fun SendNotifications() {
        requestQueue = Volley.newRequestQueue(mActivity)
        val mainObj = JSONObject()
        try {
            mainObj.put("to", userFcmToken)
            val notiObject = JSONObject()
            notiObject.put("title", title)
            notiObject.put("body", body)
            notiObject.put("icon", R.drawable.back) // enter icon that exists in drawable only
            mainObj.put("notification", notiObject)
            val request: JsonObjectRequest = object : JsonObjectRequest(
                Method.POST, postUrl, mainObj,
                Response.Listener {
                    Log.d("res",it.toString())
                },
                Response.ErrorListener { }) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): kotlin.collections.Map<String, String> {
                    val header: MutableMap<String, String> = HashMap()
                    header["content-type"] = "application/json"
                    header["authorization"] = "key=$fcmServerKey"
                    return header
                }
            }
            requestQueue!!.add(request)
        } catch (e: JSONException) {
            Log.d("error",e.localizedMessage)
        }
    }
}