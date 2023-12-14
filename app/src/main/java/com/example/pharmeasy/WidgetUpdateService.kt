/*
package com.example.pharmeasy

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat


class WidgetUpdateService : Service() {

    private val CHANNEL_ID: String = "widget_update_channel"
    private val LOCATION_SERVICE_NOTIF_ID: Int = 1100
    private val SERVICE_LOCATION_REQUEST_CODE: Int = 110
    private var handler: Handler? = null
    private val UPDATE_INTERVAL = 10000L // Update every 10 seconds

    var isEven = true

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        handler = Handler()
        prepareForegroundNotification()
        startWidgetUpdate()
        return START_STICKY
    }

    private fun startWidgetUpdate() {
        handler?.postDelayed(object : Runnable {
            override fun run() {

                Log.e("!!!!!!!!!","handler called")
                // You can use RemoteViews to update the App Widget
                val views = RemoteViews(packageName, R.layout.med_tracker_app_widget)
                val time = System.currentTimeMillis()
                views.setTextViewText(R.id.appwidget_text, time.toString())

                if(isEven){
                    views.setImageViewResource(R.id.iv_image, R.drawable.ic_android_black)
                }else{
                    views.setImageViewResource(R.id.iv_image, R.drawable.baseline_accessibility)
                }
                isEven = !isEven
                val componentName = ComponentName(
                    applicationContext,
                    MedTrackerAppWidget::class.java
                )
                AppWidgetManager.getInstance(applicationContext)
                    .updateAppWidget(componentName, views)

                // Repeat the update after the specified interval
                handler?.postDelayed(this, UPDATE_INTERVAL)
            }
        }, UPDATE_INTERVAL)
    }

    private fun prepareForegroundNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Widget Update Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager.createNotificationChannel(serviceChannel)
        }
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            SERVICE_LOCATION_REQUEST_CODE,
            notificationIntent, PendingIntent.FLAG_IMMUTABLE
        )
        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(getString(R.string.app_name))
            .setContentText(getString(R.string.app_notification_description))
//            .setSmallIcon(R.drawable.ic_android_black)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(LOCATION_SERVICE_NOTIF_ID, notification)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.e("!!!!!!!!!","service create $this")
    }

    override fun onDestroy() {
        super.onDestroy()
        handler?.removeCallbacksAndMessages(null)
        Log.e("!!!!!!!!!","service destroy $this")
    }
}*/
