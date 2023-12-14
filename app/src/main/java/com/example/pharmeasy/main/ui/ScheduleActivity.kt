package com.example.pharmeasy.main.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.pharmeasy.R
import com.example.pharmeasy.adapter.MedicineScheduleAdapter
import com.example.pharmeasy.main.presenter.ScheduleActivityPresenterImpl
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pharmeasy.activity.BaseActivity
import com.example.pharmeasy.databinding.ActivityScheduleBinding
import com.example.pharmeasy.model.MedicineSchedule
import com.example.pharmeasy.model.Schedule
import com.example.pharmeasy.service.HackeasyService
import com.pharmeasy.picker.retro.retro
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers

import java.util.concurrent.TimeUnit

class ScheduleActivity : BaseActivity() {

    lateinit var mPresenter: ScheduleActivityPresenterImpl
    var adapter: MedicineScheduleAdapter? = null
    lateinit var recyclerView:RecyclerView

    private val compositeDisposable = CompositeDisposable()
    private val service by lazy {
        retro(HackeasyService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:com.example.pharmeasy.databinding.ActivityScheduleBinding = DataBindingUtil.setContentView(this,R.layout.activity_schedule)
        recyclerView = findViewById(R.id.recycler_view)
        setContentView(R.layout.activity_schedule)

        getSchedule()
    }


    fun getSchedule(){
        compositeDisposable.add(service.getSchedule()
            .debounce(600, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    initializeAdapter(result.details)
                },
                { error ->
                    message(error.message.toString())
                }
            )
        )
    }

    fun initializeAdapter(list:List<Schedule>){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MedicineScheduleAdapter(this,list)
        recyclerView.adapter=adapter
    }


}