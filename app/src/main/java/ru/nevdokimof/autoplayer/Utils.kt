package ru.nevdokimof.autoplayer

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi

object Utils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannels(context: Context) {
        createStatusNotificationChannel(context)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createStatusNotificationChannel(context: Context) {
        val channel = NotificationChannel(
            Constants.STATUS_NOTIFICATION_CHANNEL_ID,
            context.resources.getString(R.string.status_notification_channel_name),
            NotificationManager.IMPORTANCE_NONE
        )
        channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val notificationService =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationService.createNotificationChannel(channel)
    }
}