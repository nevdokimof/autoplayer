package ru.nevdokimof.autoplayer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startForegroundService

class AutoStartBootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let { startBluetoothTrackingService(it) }
    }

    private fun startBluetoothTrackingService(context: Context) {
        val bluetoothTrackingServiceIntent =
            Intent(context, BluetoothTrackingService::class.java)
        startForegroundService(context, bluetoothTrackingServiceIntent)
    }
}