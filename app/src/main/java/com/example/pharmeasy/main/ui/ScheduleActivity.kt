package com.example.pharmeasy.main.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.pharmeasy.R
import com.example.pharmeasy.adapter.MedicineScheduleAdapter
import com.example.pharmeasy.main.presenter.ScheduleActivityPresenterImpl
import androidx.databinding.DataBindingUtil
import com.example.pharmeasy.model.MedicineSchedule

class ScheduleActivity : AppCompatActivity() {

    lateinit var mPresenter: ScheduleActivityPresenterImpl
    var adapter: MedicineScheduleAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val binding:com.example.pharmeasy.databinding.ActivityScheduleBinding = DataBindingUtil.setContentView(this,R.layout.activity_schedule)

        setContentView(R.layout.activity_schedule)
    }


    fun initializeAdapter(schedule: ArrayList<MedicineSchedule>){
//        recycler_view
    }
}