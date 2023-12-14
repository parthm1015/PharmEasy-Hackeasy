package com.example.pharmeasy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmeasy.R
import com.example.pharmeasy.model.MedicineSchedule
import com.example.pharmeasy.model.Schedule

class MedicineScheduleAdapter(private val context: Context, private val list: List<Schedule>): RecyclerView.Adapter<MedicineScheduleAdapter.MedicineScheduleHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineScheduleHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_schedule_details, parent, false)
        return MedicineScheduleHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MedicineScheduleHolder, position: Int) {
        val medicineSchedule = list[position]
    }

    class MedicineScheduleHolder(val view: View) : RecyclerView.ViewHolder(view) {

//        val time = view.te

    }
}