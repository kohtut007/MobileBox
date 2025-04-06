package com.homeworks.mobilebox.presentation.ui.onboarding.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.homeworks.mobilebox.presentation.ui.onboarding.fragment.FirstOnBoardingFragment
import com.homeworks.mobilebox.presentation.ui.onboarding.fragment.SecondOnBoardingFragment
import com.homeworks.mobilebox.presentation.ui.onboarding.fragment.ThirdOnBoardingFragment

class OnboardingPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FirstOnBoardingFragment()
            1 -> SecondOnBoardingFragment()
            2 -> ThirdOnBoardingFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}
