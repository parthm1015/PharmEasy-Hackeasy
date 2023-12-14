package com.example.pharmeasy.service

import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.pharmeasy.data.AppSharedPrefrences
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MyFirebaseMessagingService: FirebaseMessagingService() {

    private val TAG = MyFirebaseMessagingService::class.java.simpleName

    private var broadcastManager: LocalBroadcastManager? = null
    private val compositeDisposable = CompositeDisposable()


    override fun onCreate() {
        broadcastManager = LocalBroadcastManager.getInstance(this)
    }


    override fun onNewToken(token: String) {
        super.onNewToken(token)
        AppSharedPrefrences.getAppSharedPrefrencesInstace().fcmToken = token
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.

        // Check if message contains a notification payload.
        remoteMessage?.notification?.let {
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}