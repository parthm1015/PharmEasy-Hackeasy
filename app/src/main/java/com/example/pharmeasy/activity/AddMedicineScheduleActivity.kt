package com.example.pharmeasy.activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.CompoundButton
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pharmeasy.R
import com.example.pharmeasy.Result
import com.example.pharmeasy.adapter.CustomAdapter
import com.example.pharmeasy.data.DoseItem
import com.example.pharmeasy.data.SearchProductResponse
import com.example.pharmeasy.databinding.ActivityAddMedicineScheduleBinding
import com.example.pharmeasy.retro.RetrofitClientInstance
import com.example.pharmeasy.service.HackeasyService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.ArrayList
import java.util.Calendar


class AddMedicineScheduleActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    lateinit var binding: ActivityAddMedicineScheduleBinding

    private val service by lazy {
        RetrofitClientInstance.getRetrofitInstance().create(HackeasyService::class.java)
    }

    private var mQuery: String = ""
    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddMedicineScheduleBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        //radiogroup
        binding.radioGroup.setOnCheckedChangeListener { radioGroup, i ->

            if (i== R.id.rb_daily){
                binding.linearLayout.visibility = View.GONE
            }else{
                binding.linearLayout.visibility = View.VISIBLE
            }

            val radioButton = findViewById<RadioButton>(i)
        }

        binding.cbMon.setOnCheckedChangeListener(this)
        binding.cbTue.setOnCheckedChangeListener(this)
        binding.cbWed.setOnCheckedChangeListener(this)
        binding.cbThurs.setOnCheckedChangeListener(this)
        binding.cbFri.setOnCheckedChangeListener(this)
        binding.cbSat.setOnCheckedChangeListener(this)
        binding.cbSun.setOnCheckedChangeListener(this)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        //setting default dates for start and end dates
        binding.etStartDate.setText("$day - ${month + 1} - $year")
        binding.etEndDate.setText("$day - ${month + 1} - ${year+1}")

        binding.etStartDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this, { view, year, monthOfYear, dayOfMonth ->
                    binding.etStartDate.setText("$dayOfMonth - ${monthOfYear + 1} - $year")
                }, year, month, day)
            datePickerDialog.show()
        }

        binding.etEndDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this, { view, year, monthOfYear, dayOfMonth ->
                    binding.etEndDate.setText("$dayOfMonth - ${monthOfYear + 1} - $year")
            }, year+1, month, day)
            datePickerDialog.show()
        }

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean { return false }
            override fun onQueryTextChange(newText: String): Boolean {
                mQuery = newText
                return false
            }
        })


        binding.rvDoses.layoutManager = LinearLayoutManager(this)
        val dataset = ArrayList<DoseItem>()
        dataset.add(DoseItem("08:00 AM", 2))
        dataset.add(DoseItem("12:30 PM", 1))
        val customAdapter = CustomAdapter(dataset)
        binding.rvDoses.adapter = customAdapter

    }

    private val coroutineExceptionProductSearchHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, exception ->
            lifecycleScope.launch(Dispatchers.Main){
                Toast.makeText(this@AddMedicineScheduleActivity,"Something went wrong. Please Try again",Toast.LENGTH_SHORT).show()
            }
        }

    private fun searchProducts() {
        searchJob?.cancel()

        searchJob = lifecycleScope.launch(Dispatchers.IO + coroutineExceptionProductSearchHandler) {
            val res : Result<SearchProductResponse> = withContext(Dispatchers.IO){
                try {
                    val response = service.searchProducts("", mQuery)
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
                Log.d("beta", "searchProducts: "+ res.data.toString())
            }else{
                Toast.makeText(this@AddMedicineScheduleActivity, "Error Fetcing Schedule", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCheckedChanged(p0: CompoundButton, p1: Boolean) {
        when(p0.id){
            R.id.cb_mon -> Toast.makeText(this@AddMedicineScheduleActivity, "${p0.id} $p1", Toast.LENGTH_SHORT).show()
            R.id.cb_tue -> Toast.makeText(this@AddMedicineScheduleActivity, "${p0.id} $p1", Toast.LENGTH_SHORT).show()
            R.id.cb_wed -> Toast.makeText(this@AddMedicineScheduleActivity, "${p0.id} $p1", Toast.LENGTH_SHORT).show()
            R.id.cb_thurs -> Toast.makeText(this@AddMedicineScheduleActivity, "${p0.id} $p1", Toast.LENGTH_SHORT).show()
            R.id.cb_fri -> Toast.makeText(this@AddMedicineScheduleActivity, "${p0.id} $p1", Toast.LENGTH_SHORT).show()
            R.id.cb_sat -> Toast.makeText(this@AddMedicineScheduleActivity, "${p0.id} $p1", Toast.LENGTH_SHORT).show()
            R.id.cb_sun -> Toast.makeText(this@AddMedicineScheduleActivity, "${p0.id} $p1", Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(this@AddMedicineScheduleActivity, "nothing selected", Toast.LENGTH_SHORT).show()
        }
    }
}