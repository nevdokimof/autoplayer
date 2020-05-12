package ru.nevdokimof.autoplayer

import android.bluetooth.BluetoothProfile
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.view.KeyEvent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.nevdokimof.autoplayer.Constants.ACTION_BLUETOOTH_DEVICE_CONNECTION_STATE_CHANGED

class BluetoothEventsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action != ACTION_BLUETOOTH_DEVICE_CONNECTION_STATE_CHANGED)
            return

        val connectionState =
            intent.extras?.getInt(BluetoothProfile.EXTRA_STATE, BluetoothProfile.STATE_DISCONNECTED)
        if (connectionState == BluetoothProfile.STATE_CONNECTED)
            emulateHardwarePlayButton(context)
    }

    private fun emulateHardwarePlayButton(context: Context?) {
        GlobalScope.launch {
            val audioManager = context?.getSystemService(Context.AUDIO_SERVICE) as AudioManager
            audioManager.dispatchMediaKeyEvent(KEY_EVENT_MEDIA_PLAY_DOWN)
            delay(50)
            audioManager.dispatchMediaKeyEvent(KEY_EVENT_MEDIA_PLAY_UP)
        }
    }

    companion object {
        private val KEY_EVENT_MEDIA_PLAY_DOWN =
            KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PLAY)

        private val KEY_EVENT_MEDIA_PLAY_UP =
            KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MEDIA_PLAY)
    }
}