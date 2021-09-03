package com.example.ispy.domain.usecase

import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.example.ispy.domain.util.NewHintWithImage

interface HintPhotoActivityProvider {
    fun provide(callback: (Uri?) -> Unit): ActivityResultLauncher<Uri>
}

class HintPhotoActivityProviderImpl(
    private val activity: AppCompatActivity
): HintPhotoActivityProvider {
    override fun provide(callback: (Uri?) -> Unit): ActivityResultLauncher<Uri> {
        return activity.registerForActivityResult(NewHintWithImage(), callback)
    }
}
