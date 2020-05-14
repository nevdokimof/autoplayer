package ru.nevdokimof.autoplayer

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_host.*

class HostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)

        menu_bottom.setupWithNavController(nav_host_fragment.findNavController())

        startBluetoothTrackingService()
    }

    private fun startBluetoothMonitoringService() {
        val bluetoothMonitoringServiceIntent =
            Intent(applicationContext, BluetoothMonitoringService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            startForegroundService(bluetoothMonitoringServiceIntent)
        else
            startService(bluetoothMonitoringServiceIntent)
    }
}
