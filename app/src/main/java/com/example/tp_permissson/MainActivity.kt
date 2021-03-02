package com.example.tp_permissson

import android.Manifest
import android.R
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.PopupWindow
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.tp_permissson.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val GPS_CODE = 121
    lateinit var GPSButton: Button
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.infoText.text = "Bienvenue sur notre application, pour avoir votre position cliquer sur le bouton 'Autoriser l'accès"
        var permissionState = ContextCompat.checkSelfPermission(baseContext, Manifest.permission.ACCESS_FINE_LOCATION)
        if (permissionState == PackageManager.PERMISSION_DENIED) {
            binding.infoText.text = "Vous devez autoriser l'accès au GPS pour qu'on puisse récupérer votre position"
        }
        if (permissionState == PackageManager.PERMISSION_GRANTED) {
            binding.gpsBtn.text = "OK!"
        }
        GPSButton = binding.gpsBtn

        GPSButton.setOnClickListener {
            gpBtnClicked()
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            GPS_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if ((
                    grantResults.isNotEmpty() &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED
                    )
                ) {
                    binding.gpsBtn.text = "ok!"
                } else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }

    fun gpBtnClicked() {
        if (ContextCompat.checkSelfPermission(baseContext, Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is already available
        } else {
            // Permission is missing and must be requested.
            requestGPSPermission()
        }
    }
    fun requestGPSPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            val popUp = PopupWindow()
            popUp.showAtLocation(binding.mainLayout, Gravity.BOTTOM, 10, 10)
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                GPS_CODE
            )
        }
        if (ContextCompat.checkSelfPermission(baseContext, Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_DENIED
        ) {

        }
    }
}
