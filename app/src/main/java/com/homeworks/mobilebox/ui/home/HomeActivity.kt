package com.homeworks.mobilebox.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.homeworks.mobilebox.R
import com.homeworks.mobilebox.databinding.ActivityHomeBinding
import com.homeworks.mobilebox.ui.auth.LoginActivity
import com.homeworks.mobilebox.ui.fragments.AndroidFragment
import com.homeworks.mobilebox.ui.fragments.AppleFragment
import com.homeworks.mobilebox.ui.fragments.FilterBottomSheet
import com.homeworks.mobilebox.ui.fragments.HomeFragment
import com.homeworks.mobilebox.ui.fragments.WishlistFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpToolbar()
        setUpDrawer()
        setUpBottomNav()
        setUpLogout()
        catchEvent()
        loadFragment(HomeFragment())
    }

    private fun setUpToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Home"
            setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white))
    }

    private fun setUpDrawer() {
        toggle = ActionBarDrawerToggle(
            this,
            binding.homeDrawerLayout,
            R.string.open,
            R.string.close
        )
        binding.homeDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun setUpBottomNav() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> loadFragment(HomeFragment())
                R.id.apple -> loadFragment(AppleFragment())
                R.id.android -> loadFragment(AndroidFragment())
                R.id.wishlist -> loadFragment(WishlistFragment())
                else -> return@setOnItemSelectedListener false
            }
            true
        }
    }

    private fun setUpLogout() {
        val headerView = binding.navView.getHeaderView(0)
        val tvLogout = headerView.findViewById<TextView>(R.id.tvLogout)

        tvLogout.setOnClickListener {
            binding.homeDrawerLayout.closeDrawer(GravityCompat.START)

            binding.homeDrawerLayout.addDrawerListener(object :
                DrawerLayout.SimpleDrawerListener() {
                override fun onDrawerClosed(drawerView: View) {
                    startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
                    finish()
                    binding.homeDrawerLayout.removeDrawerListener(this)
                }
            })
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.home_nav_host_fragment, fragment)
            .commit()
    }

    private fun catchEvent() {
        // Your event handling code here
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) return true

        return when (item.itemId) {
            R.id.menu_filter -> {
                FilterBottomSheet().show(supportFragmentManager, "FilterBottomSheet")
                true
            }

            R.id.menu_search -> {
                Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.menu_cart -> {
                Toast.makeText(this, "Cart clicked", Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun getDrawerToggleDelegate(): ActionBarDrawerToggle.Delegate? {
        return super.getDrawerToggleDelegate()
    }
}

