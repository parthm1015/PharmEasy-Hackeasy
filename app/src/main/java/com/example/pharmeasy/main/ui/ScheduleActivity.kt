package com.example.pharmeasy.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pharmeasy.Result
import com.example.pharmeasy.activity.BaseActivity
import com.example.pharmeasy.adapter.MedicineScheduleAdapter
import com.example.pharmeasy.databinding.ActivityScheduleBinding
import com.example.pharmeasy.main.presenter.ScheduleActivityPresenterImpl
import com.example.pharmeasy.model.MedicineSchedule
import com.example.pharmeasy.model.Schedule
import com.example.pharmeasy.retro.RetrofitClientInstance
import com.example.pharmeasy.service.HackeasyService
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScheduleActivity : BaseActivity() {

    lateinit var mPresenter: ScheduleActivityPresenterImpl
    var adapter: MedicineScheduleAdapter? = null
    lateinit var binding: ActivityScheduleBinding

    private val compositeDisposable = CompositeDisposable()
    private val service by lazy {
        RetrofitClientInstance.getRetrofitInstance().create(HackeasyService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        getSchedule()
    }

    fun getSchedule(){
        lifecycleScope.launch {
            val res : Result<MedicineSchedule> = withContext(Dispatchers.IO){
                try {
                    val response = service.getSchedule()
                    if(response.isSuccessful){
                        return@withContext Result.Success(response.body()!!)
                    }else{
                        return@withContext Result.Error(Exception(response.errorBody().toString()))
                    }
                }catch (excep: Exception){
                    return@withContext Result.Error(excep)
                }

            }
            if(res is Result.Success){
                initializeAdapter(res.data.scheduleByTime ?: hashMapOf())
            }else{
                Toast.makeText(this@ScheduleActivity, "Error Fetcing Schedule", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun initializeAdapter(hashMap: HashMap<String, List<Schedule>>?){
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MedicineScheduleAdapter(hashMap)
        binding.recyclerView.adapter=adapter
    }


}