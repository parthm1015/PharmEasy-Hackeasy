package com.example.pharmeasy

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

class WidgetFetchStatusWorker(
    private val context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    var isEven = true
    object FileParams {
        const val KEY_IS_NEXT_UPDATE_REQUIRED = "key_next_update_required"
    }

    override suspend fun doWork(): Result {
        val isNextUpdateRequired = inputData.getBoolean(FileParams.KEY_IS_NEXT_UPDATE_REQUIRED, false)
        Log.e("!!!!!!!!","worker caller next update $isNextUpdateRequired")
        delay(3000)
        updateWidgetView(context)

        if(isNextUpdateRequired){
            Log.e("!!!!!!!!","next worker scheduled")

            val  mywork = OneTimeWorkRequest.Builder(WidgetFetchStatusWorker::class.java)
                .setInitialDelay(1, TimeUnit.MINUTES)// Use this when you want to add initial delay or schedule initial work to `OneTimeWorkRequest` e.g. setInitialDelay(2, TimeUnit.HOURS)
                .build()
            WorkManager.getInstance(applicationContext).enqueue(mywork)
        }
        return Result.success()
    }

    private fun updateWidgetView(context: Context){
        Log.e("!!!!!!!","update views")
        val views = RemoteViews(context.packageName, R.layout.med_tracker_app_widget)
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
    }
}