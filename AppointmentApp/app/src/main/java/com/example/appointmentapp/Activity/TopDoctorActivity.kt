package com.example.appointmentapp.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appointmentapp.Adapter.TopDoctorAdapter
import com.example.appointmentapp.Adapter.TopDoctorAdapter2
import com.example.appointmentapp.R
import com.example.appointmentapp.ViewModel.MainViewModel
import com.example.appointmentapp.databinding.ActivityTopDoctorBinding

class TopDoctorActivity : AppCompatActivity() {
    private lateinit var binding : ActivityTopDoctorBinding
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTopDoctors()
    }
    private fun initTopDoctors() {

        binding.progressBarTopDoc.visibility = View.VISIBLE
        viewModel.doctors.observe(this@TopDoctorActivity, Observer {
            binding.topDoctorList.layoutManager = LinearLayoutManager(this@TopDoctorActivity,
                LinearLayoutManager.VERTICAL,false)
            binding. topDoctorList.adapter = TopDoctorAdapter2(it)
            binding.progressBarTopDoc.visibility = View.GONE

        })

        viewModel.loadDoctors()
        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}