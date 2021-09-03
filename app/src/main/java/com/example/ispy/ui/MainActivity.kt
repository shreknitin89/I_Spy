package com.example.ispy.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.ispy.R
import com.example.ispy.databinding.ActivityMainBinding
import com.example.ispy.ui.hints.HintsListFragment
import com.example.ispy.ui.newhint.NewHintFragment

class MainActivity : AppCompatActivity(), HintRouter {
    private lateinit var activityBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(activityBinding.root)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, HintsListFragment.newInstance())
            commit()
        }
    }

    override fun route(uri: Uri) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, NewHintFragment.newInstance(uri))
            commit()
        }
    }
}
