package ru.nevdokimof.autoplayer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startForegroundService

class AutoStartBootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
            context?.let { startBluetoothMonitoringService(it) }
    }

    private fun startBluetoothMonitoringService(context: Context) {
        val bluetoothMonitoringServiceIntent =
            Intent(context, BluetoothMonitoringService::class.java)
        startForegroundService(context, bluetoothMonitoringServiceIntent)
    }
}