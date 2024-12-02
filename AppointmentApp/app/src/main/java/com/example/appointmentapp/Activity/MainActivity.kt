package com.example.appointmentapp.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appointmentapp.Adapter.CategoryAdapter
import com.example.appointmentapp.Adapter.TopDoctorAdapter
import com.example.appointmentapp.R
import com.example.appointmentapp.ViewModel.MainViewModel
import com.example.appointmentapp.databinding.ActivityIntroBinding
import com.example.appointmentapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCategory()
        initTopDoctors()

    }

    private fun initTopDoctors() {

            binding.progressBar2.visibility = View.VISIBLE
            viewModel.doctors.observe(this@MainActivity, Observer {
                binding.viewTopDoctor.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
               binding. viewTopDoctor.adapter = TopDoctorAdapter(it)
                binding.progressBar2.visibility = View.GONE

            })

            viewModel.loadDoctors()
        binding.topDoctorTxt.setOnClickListener {
            startActivity(Intent(this@MainActivity,TopDoctorActivity::class.java))
        }
        }


    private fun initCategory() {
        binding.progressBarCategory.visibility = View.VISIBLE
        viewModel.category.observe(this@MainActivity, Observer {
            binding.viewCategory.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            binding.viewCategory.adapter = CategoryAdapter(it)
            binding.progressBarCategory.visibility = View.GONE
        })
        viewModel.loadCategory()
    }
}