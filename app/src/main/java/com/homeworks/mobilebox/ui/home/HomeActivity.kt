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

        setUpDrawer()
        setUpToolbar()
        setUpBottomNav()
        catchEvent()
        loadFragment(HomeFragment())

        // Inside your HomeActivity, after initializing your NavigationView:
        val headerView = binding.navView.getHeaderView(0)
        val tvLogout = headerView.findViewById<TextView>(R.id.tvLogout)
        tvLogout.setOnClickListener {
            // Close the drawer first
            binding.homeDrawerLayout.closeDrawer(GravityCompat.START)
            // Add a listener to wait until the drawer is fully closed
            binding.homeDrawerLayout.addDrawerListener(object : DrawerLayout.SimpleDrawerListener() {
                override fun onDrawerClosed(drawerView: View) {
                    // Once the drawer is closed, navigate to LoginActivity
                    startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
                    finish()
                    // Remove this listener to prevent multiple calls
                    binding.homeDrawerLayout.removeDrawerListener(this)
                }
            })
        }

    }

    private fun setUpDrawer() {
        toggle = ActionBarDrawerToggle(this,binding.homeDrawerLayout, R.string.open, R.string.close)
        binding.homeDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpBottomNav() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                }

                R.id.apple -> {
                    loadFragment(AppleFragment())
                }

                R.id.android -> {
                    loadFragment(AndroidFragment())
                }

                R.id.wishlist -> {
                    loadFragment(WishlistFragment())
                }
                else -> false
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.home_nav_host_fragment, fragment)
        transaction.commit()
    }

    private fun setUpToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Home"
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white))
    }

    private fun catchEvent() {
        // Your event handling code here
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // First, let the drawer toggle handle home button events.
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        when (item.itemId) {
            R.id.menu_filter -> {
                // Create and show the FilterBottomSheet
                val filterBottomSheet = FilterBottomSheet()
                filterBottomSheet.show(supportFragmentManager, "FilterBottomSheet")
                return true
            }
            R.id.menu_search -> {
                Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.menu_cart -> {
                Toast.makeText(this, "Cart clicked", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getDrawerToggleDelegate(): ActionBarDrawerToggle.Delegate? {
        return super.getDrawerToggleDelegate()
    }

}
