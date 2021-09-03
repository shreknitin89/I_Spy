package com.example.ispy.domain.usecase

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


interface CameraPermissionsProvider {
    fun provide(callback: (Boolean) -> Unit): ActivityResultLauncher<String>
}

class CameraPermissionsLauncher(
    private val activity: AppCompatActivity
) : CameraPermissionsProvider {
    override fun provide(callback: (Boolean) -> Unit): ActivityResultLauncher<String> {
        return activity.registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isPermissionGranted ->
            callback(isPermissionGranted)
        }
    }
}
