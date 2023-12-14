package com.example.pharmeasy.service

import com.example.pharmeasy.model.MedicineSchedule
import io.reactivex.Observable
import retrofit2.http.GET

interface HackeasyService {

    @GET("/atlas/schedule/2")
    fun getSchedule(): Observable<MedicineSchedule>
}