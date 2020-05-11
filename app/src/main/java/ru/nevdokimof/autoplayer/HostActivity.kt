package ru.nevdokimof.autoplayer

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class HostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)

        startBluetoothTrackingService()
    }

    private fun startBluetoothTrackingService() {
        val bluetoothTrackingServiceIntent =
            Intent(applicationContext, BluetoothTrackingService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            startForegroundService(bluetoothTrackingServiceIntent)
        else
            startService(bluetoothTrackingServiceIntent)
    }
}
