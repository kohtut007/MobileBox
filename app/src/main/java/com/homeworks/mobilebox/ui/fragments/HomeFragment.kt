package com.homeworks.mobilebox.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.homeworks.mobilebox.R
import com.homeworks.mobilebox.databinding.FragmentHomeBinding
import com.homeworks.mobilebox.ui.ProductDetailActivity
import com.homeworks.mobilebox.ui.adapters.ProductAdapter

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpImageSlideShow()
        setUpRecyclerView()
        catchEvent()
    }

    private fun setUpRecyclerView() {
        productAdapter = ProductAdapter()
        binding.rvProduct.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvProduct.adapter = productAdapter

        productAdapter.setOnItemClickListener { position ->
            startActivity(Intent(requireContext(), ProductDetailActivity::class.java))
        }
    }

    private fun setUpImageSlideShow() {
        val imageSlider = binding.imageSlider
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.iv_phone))
        imageList.add(SlideModel(R.drawable.iv_redmi))
        imageList.add(SlideModel(R.drawable.iv_tecno_camon))

        // Set images and scale type
        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)
    }

    private fun catchEvent() {
        // Additional event handling can be placed here.
    }
}
