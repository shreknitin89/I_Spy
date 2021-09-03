package com.example.ispy.domain.usecase

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

interface PhotoFileUriGenerator {
    fun generateNewPhotoFile(): Uri
}

class PhotoFileUriGeneratorImpl constructor(
    private val context: Context
) : PhotoFileUriGenerator {

    override fun generateNewPhotoFile(): Uri {
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val file = createTempJPEGImageFile(storageDir, timeStamp)
        return FileProvider.getUriForFile(context, "com.example.ispy.fileprovider", file)
    }

    private fun createTempJPEGImageFile(storageDir: File?, timeStamp: String): File {
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        )
    }
}
