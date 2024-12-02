package com.example.appointmentapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.appointmentapp.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    private lateinit var binding : ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
       binding.button.setOnClickListener {
           startActivity(Intent(this, MainActivity::class.java))
       }
    }
}