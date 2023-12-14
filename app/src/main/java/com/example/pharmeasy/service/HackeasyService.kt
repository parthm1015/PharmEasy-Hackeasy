package com.example.pharmeasy.service

import com.example.pharmeasy.model.MedicineSchedule
import retrofit2.Response
import retrofit2.http.GET

interface HackeasyService {

    @GET("api/atlas/schedule/6")
    suspend fun getSchedule(): Response<MedicineSchedule>
}