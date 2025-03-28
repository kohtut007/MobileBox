package com.homeworks.mobilebox.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.homeworks.mobilebox.databinding.ActivityFirstOnBoardingBinding

class FirstOnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        catchEvent()
    }

    private fun catchEvent() {
        binding.btnNext.setOnClickListener {
            startActivity(Intent(this, SecondOnBoardingActivity::class.java))
        }
    }
}