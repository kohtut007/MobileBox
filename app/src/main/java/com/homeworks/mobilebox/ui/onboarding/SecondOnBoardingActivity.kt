package com.homeworks.mobilebox.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.homeworks.mobilebox.R
import com.homeworks.mobilebox.databinding.ActivitySecondOnBoardingBinding

class SecondOnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        catchEvent()
    }

    private fun catchEvent() {
        binding.btnPrevious.setOnClickListener {
            finish()
        }

        binding.btnNext.setOnClickListener {
            startActivity(Intent(this@SecondOnBoardingActivity, ThirdOnBoardingActivity::class.java))
        }
    }
}