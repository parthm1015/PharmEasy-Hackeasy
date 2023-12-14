package com.example.pharmeasy

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class ScheduleApplication: Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var mContext: Context? = null

        fun getApplicationContex(): Context {
            return mContext!!
        }

        fun getFetchrApplicationClass(): ScheduleApplication {
            return mContext as ScheduleApplication

        }
    }

}