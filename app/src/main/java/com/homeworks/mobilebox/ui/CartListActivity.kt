package com.homeworks.mobilebox.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.homeworks.mobilebox.R
import com.homeworks.mobilebox.databinding.ActivityCartListBinding
import com.homeworks.mobilebox.ui.adapters.CartAdapter

class CartListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartListBinding
    private lateinit var cartAdapter: CartAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        catchEvent()
    }

    private fun setUpRecyclerView() {
        cartAdapter = CartAdapter()
        binding.rvCartList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvCartList.adapter = cartAdapter

    }

    private fun catchEvent() {
        binding.btnCheckOut.setOnClickListener {
            startActivity(Intent(this@CartListActivity, CheckOutActivity::class.java))
        }
    }
}