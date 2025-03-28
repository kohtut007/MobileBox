package com.homeworks.mobilebox.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.homeworks.mobilebox.databinding.ActivityThirdOnBoardingBinding
import com.homeworks.mobilebox.ui.auth.LoginActivity

class ThirdOnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        catchEvent()
    }

    private fun catchEvent() {
        binding.btnPrevious.setOnClickListener {
            finish()
        }
        binding.btnLetsStart.setOnClickListener {
            startActivity(Intent(this@ThirdOnBoardingActivity, LoginActivity::class.java))
        }
    }
}