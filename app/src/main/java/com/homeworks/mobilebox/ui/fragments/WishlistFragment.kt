package com.homeworks.mobilebox.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.homeworks.mobilebox.R
import com.homeworks.mobilebox.databinding.FragmentAndroidBinding
import com.homeworks.mobilebox.databinding.FragmentWishlistBinding
import com.homeworks.mobilebox.ui.ProductDetailActivity
import com.homeworks.mobilebox.ui.adapters.ProductAdapter

class WishlistFragment : Fragment() {
    private lateinit var binding: FragmentWishlistBinding
    private lateinit var productAdapter: ProductAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        productAdapter = ProductAdapter()
        binding.rvProductWishlist.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvProductWishlist.adapter = productAdapter

        productAdapter.setOnItemClickListener { position ->
            startActivity(Intent(requireContext(), ProductDetailActivity::class.java))
        }
    }
}