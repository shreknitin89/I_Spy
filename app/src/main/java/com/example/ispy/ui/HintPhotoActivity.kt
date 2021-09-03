package com.example.ispy.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity

class HintPhotoActivity : AppCompatActivity() {

    companion object {
        private const val ARG_URI = "ARG_URI"
        fun createIntent(context: Context, uri: Uri): Intent {
            val intent = Intent(context, HintPhotoActivity::class.java)
            intent.putExtra(ARG_URI, uri)
            return intent
        }
    }
}
