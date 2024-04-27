package com.example.hackathonapp.Domaine

import android.app.Application
import android.content.Context
import com.example.hackathonapp.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class NotificationReceiver @Inject constructor(
    private val context: Application
): FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)

    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        NotificationsService(context).showNotification("Test",
            NotificationsService.NOTIFICATIONS_CHANNEL_ID,0,"test")
    }
}