package com.example.ispy.domain.usecase

import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

interface CameraLauncherUseCase {
    fun provide(callback: (Boolean) -> Unit): ActivityResultLauncher<Uri>
}

class CameraLauncherUseCaseImpl(
    private val activity: AppCompatActivity
) : CameraLauncherUseCase {
    override fun provide(callback: (Boolean) -> Unit): ActivityResultLauncher<Uri> {
        return activity.registerForActivityResult(
            ActivityResultContracts.TakePicture()
        ) { isImageSaved ->
            callback(isImageSaved)
        }
    }
}
