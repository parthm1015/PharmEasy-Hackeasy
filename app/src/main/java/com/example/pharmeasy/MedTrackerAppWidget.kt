package com.example.pharmeasy

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit


/**
 * Implementation of App Widget functionality.
 */
class MedTrackerAppWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
//        for (appWidgetId in appWidgetIds) {
//            updateAppWidget(context, appWidgetManager, appWidgetId)
//        }
        Log.e("!!!!!!!!!!","update widget called ids - ${appWidgetIds.toList()}")
//        val serviceIntent = Intent(context, WidgetUpdateService::class.java)
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            context.startForegroundService ( serviceIntent )
//        } else {
//            context.startService ( serviceIntent )
//        }
//        val data = Data.Builder()
//
//        data.apply {
//            putBoolean(WidgetFetchStatusWorker.FileParams.KEY_IS_NEXT_UPDATE_REQUIRED, true)
//        }
//        val  mywork = OneTimeWorkRequestBuilder<WidgetFetchStatusWorker>()
//            .setInputData(data.build())
//            .build()
//        WorkManager.getInstance(context).enqueue(mywork)

    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val widgetText = context.getString(R.string.appwidget_text)
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.med_tracker_app_widget)
    views.setTextViewText(R.id.appwidget_text, "time to take medicine for 8 am slot")
//    views.setViewVisibility(R.id.appwidget_text, View.GONE)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}