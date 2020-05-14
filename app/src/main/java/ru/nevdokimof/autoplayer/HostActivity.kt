package ru.nevdokimof.autoplayer

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_host.*
import ru.nevdokimof.autoplayer.model.ServiceStatus
import ru.nevdokimof.autoplayer.viewmodels.AutoPlayerServiceViewModel

class HostActivity : AppCompatActivity() {
    private val autoPlayerServiceViewModel: AutoPlayerServiceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)

        menu_bottom.setupWithNavController(nav_host_fragment.findNavController())

        button_on_off.setOnClickListener { autoPlayerServiceViewModel.changeAutoPlayerServiceState() }

        // to prevent click-through
        layout_dim.setOnClickListener {  }

        autoPlayerServiceViewModel.autoPlayerServiceStatus.observe(this, Observer { status ->
            when (status) {
                ServiceStatus.RUNNING -> {
                    moveOnOffButtonToTopBar()
                    removeDim()
                }
                ServiceStatus.DOWN, null -> {
                    moveOnOffButtonToCenter()
                    dimScreen()
                }
            }
        })
    }

    private fun dimScreen() {
        layout_dim.visibility = View.VISIBLE
    }

    private fun removeDim() {
        layout_dim.visibility = View.GONE
    }

    private fun moveOnOffButtonToCenter() {
        with(layout_button_on_off) {
            val params = layoutParams as ConstraintLayout.LayoutParams
            params.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
            params.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
            background = null
            requestLayout()
        }
        with(button_on_off) {
            customSize = resources.getDimensionPixelSize(R.dimen.button_on_off_enlarged_dimen)
            setImageResource(R.drawable.ic_power_off_24dp)
        }
    }

    private fun moveOnOffButtonToTopBar() {
        with(layout_button_on_off) {
            val params = layoutParams as ConstraintLayout.LayoutParams
            params.topToTop = ConstraintLayout.LayoutParams.UNSET
            params.bottomToBottom = top_bar.id
            params.topToBottom = top_bar.id
            background = resources.getDrawable(R.drawable.button_on_off_background, null)
            requestLayout()
        }
        with(button_on_off) {
            clearCustomSize()
            setImageResource(R.drawable.ic_power_on_24dp)
        }
    }
}
