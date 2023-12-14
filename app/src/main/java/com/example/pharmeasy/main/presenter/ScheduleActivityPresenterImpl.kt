package com.example.pharmeasy.main.presenter

import com.example.pharmeasy.service.HackeasyService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class ScheduleActivityPresenterImpl {

//    private val compositeDisposable = CompositeDisposable()
//    private val service: HackeasyService
//
//
//    fun getSchedule(){
//        compositeDisposable.add(service.getSchedule()
//            .debounce(600, TimeUnit.MILLISECONDS)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { result ->
//                    checkBarcode(pickedItems)
//                },
//                { error ->
//                    getView().showErrors(error, pickedItems.barCode, "outward/pickerTasks/{taskId}/pickedItems:")
//                }
//            )
//        )
//    }
}