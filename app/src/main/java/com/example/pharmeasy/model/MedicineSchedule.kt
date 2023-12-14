package com.example.pharmeasy.model

import java.time.LocalDateTime

data class MedicineSchedule(
    val id:Int,
    val userProfileId: Int,
    val active: Boolean,
    val details : List<Schedule>
)

data class Schedule(
    val id: Int,
    val ucode: String,
    val medicineName: String,
    val executionTimes: List<LocalDateTime>
)
