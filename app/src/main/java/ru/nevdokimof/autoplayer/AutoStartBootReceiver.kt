package ru.nevdokimof.autoplayer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startForegroundService

class AutoStartBootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED)
            context?.let { startAutoPlayerService(it) }
    }

    private fun startAutoPlayerService(context: Context) {
        val autoPlayerServiceIntent =
            Intent(context, AutoPlayerService::class.java)
        startForegroundService(context, autoPlayerServiceIntent)
    }
}