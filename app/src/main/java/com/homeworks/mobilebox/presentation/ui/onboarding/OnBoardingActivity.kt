package com.homeworks.mobilebox.presentation.ui.onboarding

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.homeworks.mobilebox.R
import com.homeworks.mobilebox.databinding.ActivityOnBoardingBinding
import com.homeworks.mobilebox.presentation.ui.onboarding.adapter.OnboardingPagerAdapter
import com.homeworks.mobilebox.presentation.ui.login.LoginActivity
import com.homeworks.mobilebox.ui.util.viewBinding

class OnboardingActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityOnBoardingBinding::inflate)
    private lateinit var onboardingAdapter: OnboardingPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupViewPager()
        handleClicks()
    }

    private fun setupViewPager() = with(binding) {
        vpOnBoarding.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateIndicator(position)
                updateNavigationButtons(position)

            }
        })

        onboardingAdapter = OnboardingPagerAdapter(this@OnboardingActivity)
        vpOnBoarding.adapter = onboardingAdapter
    }

    private fun handleClicks() = with(binding) {
        btPrev.setOnClickListener {
            if (vpOnBoarding.currentItem > 0) {
                vpOnBoarding.currentItem--
            }
        }

        btNext.setOnClickListener {
            val currentItem = vpOnBoarding.currentItem
            val lastItem = onboardingAdapter.itemCount - 1

            if (currentItem < lastItem) {
                vpOnBoarding.currentItem = currentItem + 1
                updateIndicator(vpOnBoarding.currentItem)
            } else {
                navigateToHome()
            }
        }
    }

    private fun updateNavigationButtons(position: Int) = with(binding) {

        btNext.text = if (position == 2) {
            getString(R.string.let_s_start)
        } else {
            getString(R.string.next)
        }
        btPrev.visibility = if (position == 0) View.GONE else View.VISIBLE
    }

    private fun updateIndicator(position: Int) = with(binding) {
        val activeColor = ContextCompat.getColor(this@OnboardingActivity, R.color.colorPrimary)
        val inactiveColor =
            ContextCompat.getColor(this@OnboardingActivity, R.color.placeholder_grey)

        cvIndicator1.backgroundTintList =
            ColorStateList.valueOf(if (position == 0) activeColor else inactiveColor)

        cvIndicator2.backgroundTintList =
            ColorStateList.valueOf(if (position == 1) activeColor else inactiveColor)

        cvIndicator3.backgroundTintList =
            ColorStateList.valueOf(if (position == 2) activeColor else inactiveColor)

    }


    private fun navigateToHome() {
        Intent(this@OnboardingActivity, LoginActivity::class.java).apply {
            startActivity(this)
            finish()
        }
    }
}


