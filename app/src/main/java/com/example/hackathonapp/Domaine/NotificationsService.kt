package com.example.hackathonapp.Domaine

import android.app.Activity
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat


class NotificationsService(
    private val context: Context
) {
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(
        notificationText: String,
        channelId: String,
        id: Int,
        notificationTitle: String,
        activityClass: Class<*>
    ) {
        val activityIntent = Intent(context,activityClass)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            2,
            activityIntent,
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )

        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle(notificationTitle)
            .setContentText(notificationText)
            .setContentIntent(activityPendingIntent)
            .build()

        notificationManager.notify(id, notification)
    }

    companion object {
        const val NEWS_CHANNEL_ID = "news_channel_id"
        const val OBJECTS_CHANNEL_ID = "Objects_channel_id"
        const val COMPLAINTS_CHANNEL_ID = "complaints_channel_id"
        const val SERVICES_CHANNEL_ID = "services_channel_id"
        const val NOTIFICATIONS_CHANNEL_ID = "notifications_channel_id"
    }
}