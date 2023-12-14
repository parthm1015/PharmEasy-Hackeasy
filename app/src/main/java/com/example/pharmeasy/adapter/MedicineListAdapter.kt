package com.example.pharmeasy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmeasy.databinding.AdapterScheduleMedicineListBinding
import com.example.pharmeasy.databinding.LayoutScheduleDetailsBinding
import com.example.pharmeasy.model.Schedule

class MedicineListAdapter(private val list: List<Schedule>): RecyclerView.Adapter<MedicineListAdapter.MedicineScheduleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineScheduleViewHolder {
        val binding = AdapterScheduleMedicineListBinding.inflate(
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
        holder.bind(medicineSchedule)
    }

    class MedicineScheduleViewHolder(val view: AdapterScheduleMedicineListBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(schedule: Schedule){
            view.textPhase.text = schedule.medicineName
            view.textCount.text = schedule.dosage
        }
    }
}