package com.example.pharmeasy.activity

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.pharmeasy.MedTrackerAppWidget
import com.example.pharmeasy.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.button.setOnClickListener {
            addWidget()
        }

        binding.btnUpdateWidget.setOnClickListener {

        }

    }

    private fun addWidget(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mAppWidgetManager = getSystemService(AppWidgetManager::class.java)
            val myProvider = ComponentName(this, MedTrackerAppWidget::class.java)
            val b = Bundle()
            b.putString("ggg", System.currentTimeMillis().toString())
            if (mAppWidgetManager.isRequestPinAppWidgetSupported) {
                val pinnedWidgetCallbackIntent = Intent(this, MedTrackerAppWidget::class.java)
                val successCallback = PendingIntent.getBroadcast(
                    this, 0,
                    pinnedWidgetCallbackIntent, PendingIntent.FLAG_IMMUTABLE
                )
                mAppWidgetManager.requestPinAppWidget(myProvider, b, successCallback)
            }
        }
    }
}