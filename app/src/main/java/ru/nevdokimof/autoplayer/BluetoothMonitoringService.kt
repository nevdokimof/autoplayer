package ru.nevdokimof.autoplayer

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import ru.nevdokimof.autoplayer.Constants.ACTION_BLUETOOTH_DEVICE_CONNECTION_STATE_CHANGED
import ru.nevdokimof.autoplayer.Constants.STATUS_NOTIFICATION_CHANNEL_ID
import ru.nevdokimof.autoplayer.Constants.STATUS_NOTIFICATION_ID

class BluetoothMonitoringService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        registerBluetoothEventsReceiver()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setupAsForegroundService()
        }

        return START_STICKY
    }

    private fun registerBluetoothEventsReceiver() {
        applicationContext.registerReceiver(
            bluetoothActionsReceiver,
            bluetoothConnectedIntentFilter
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupAsForegroundService() {
        val statusNotification = Notification.Builder(this, STATUS_NOTIFICATION_CHANNEL_ID)
            .setContentTitle(resources.getString(R.string.status_notification_title))
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
        startForeground(STATUS_NOTIFICATION_ID, statusNotification)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        applicationContext.unregisterReceiver(bluetoothActionsReceiver)
    }

    companion object {
        private val bluetoothConnectedIntentFilter =
            IntentFilter(ACTION_BLUETOOTH_DEVICE_CONNECTION_STATE_CHANGED)

        private val bluetoothActionsReceiver by lazy { BluetoothEventsReceiver() }
    }
}