package com.example.pharmeasy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmeasy.R
import com.example.pharmeasy.databinding.LayoutScheduleDetailsBinding
import com.example.pharmeasy.model.Schedule

class MedicineScheduleAdapter(private val context: Context, private val list: List<Schedule>): RecyclerView.Adapter<MedicineScheduleAdapter.MedicineScheduleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineScheduleViewHolder {
        val binding = LayoutScheduleDetailsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MedicineScheduleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MedicineScheduleViewHolder, position: Int) {
        val medicineSchedule = list[position]
    }

    class MedicineScheduleViewHolder(val view: LayoutScheduleDetailsBinding) : RecyclerView.ViewHolder(view.root) {


    }
}