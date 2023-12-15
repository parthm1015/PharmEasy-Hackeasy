package com.example.pharmeasy.service

import com.example.pharmeasy.data.SearchProductResponse
import com.example.pharmeasy.model.MedicineSchedule
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HackeasyService {

    @GET("api/atlas/schedule/6")
    suspend fun getSchedule(): Response<MedicineSchedule>

    @GET("api/search/searchTypeAhead")
    suspend fun searchProducts(@Query("intent_id") i:String = "", @Query("q") s: String): Response<SearchProductResponse>
}