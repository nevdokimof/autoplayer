package ru.nevdokimof.autoplayer.viewmodels

import android.app.Application
import android.content.Intent
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import ru.nevdokimof.autoplayer.AutoPlayerService
import ru.nevdokimof.autoplayer.model.ServiceStatus

class AutoPlayerServiceViewModel(app: Application) : AndroidViewModel(app) {
    private val autoPlayerServiceIntent =
        Intent(getApplication(), AutoPlayerService::class.java)
    val autoPlayerServiceStatus = AutoPlayerService.serviceStatus

    fun changeAutoPlayerServiceState() {
        when(autoPlayerServiceStatus.value) {
            ServiceStatus.RUNNING -> stopAutoPlayerService()
            ServiceStatus.DOWN, null -> startAutoPlayerService()
        }
    }

    private fun stopAutoPlayerService() {
        getApplication<Application>().stopService(autoPlayerServiceIntent)
    }

    private fun startAutoPlayerService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            getApplication<Application>().startForegroundService(autoPlayerServiceIntent)
        else
            getApplication<Application>().startService(autoPlayerServiceIntent)
    }
}