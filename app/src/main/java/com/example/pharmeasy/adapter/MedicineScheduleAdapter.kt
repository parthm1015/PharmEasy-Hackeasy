package com.example.pharmeasy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmeasy.databinding.LayoutScheduleDetailsBinding
import com.example.pharmeasy.model.Schedule
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

class MedicineScheduleAdapter(private val list: HashMap<String, List<Schedule>>?): RecyclerView.Adapter<MedicineScheduleAdapter.MedicineScheduleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineScheduleViewHolder {
        val binding = LayoutScheduleDetailsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MedicineScheduleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: MedicineScheduleViewHolder, position: Int) {
        val medicineSchedule = list?.get(list.map { it.key }.get(position)) ?: listOf()
        holder.bind(list?.map { it.key }?.get(position) ?: "", medicineSchedule)
    }

    class MedicineScheduleViewHolder(val view: LayoutScheduleDetailsBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(date: String?, schedule: List<Schedule>?){
            view.textTime.text = formatDateString("HH:mm:ss","yyyy-MM-dd HH:mm:ss aaa", date)
            view.rvMedicineList.layoutManager = LinearLayoutManager(view.root.context)
            val adapter = MedicineListAdapter(schedule ?: listOf())
            view.rvMedicineList.adapter=adapter
        }

        fun formatDateString(to: String?, from: String?, dateString: String?): String? {
            val format1 = SimpleDateFormat(to, Locale.ENGLISH)
            val format2 = SimpleDateFormat(from, Locale.ENGLISH)
            return try {
                format1.format(format2.parse(dateString))
            } catch (e: ParseException) {
                null
            }
        }
    }

}