package com.homeworks.mobilebox.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.homeworks.mobilebox.databinding.ActivitySplashScreenBinding
import com.homeworks.mobilebox.presentation.ui.onboarding.OnboardingActivity
import com.homeworks.mobilebox.ui.util.viewBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivitySplashScreenBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        lifecycleScope.launch {
            delay(2000L)
            Intent(this@SplashScreenActivity, OnboardingActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }
    }
}