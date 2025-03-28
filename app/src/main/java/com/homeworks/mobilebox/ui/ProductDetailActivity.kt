package com.homeworks.mobilebox.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemChangeListener
import com.denzcoskun.imageslider.models.SlideModel
import com.homeworks.mobilebox.R
import com.homeworks.mobilebox.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpImageSlideShow()
        catchEvent()
    }

    private fun setUpImageSlideShow() {
        val imageSlider = binding.imageSlider
        val imageList = ArrayList<SlideModel>()

        // Add images
        imageList.add(SlideModel(R.drawable.iv_iphone_white))
        imageList.add(SlideModel(R.drawable.img))
        imageList.add(SlideModel(R.drawable.img_1))

        // Set images and scale type
        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)
        binding.tvPageCount.text = "1/ ${imageList.size}"


        // Use an object expression for the ItemChangeListener
        imageSlider.setItemChangeListener(object : ItemChangeListener {
            override fun onItemChanged(position: Int) {
                // position is zero-based, so add 1
                val currentSlide = position + 1
                val totalSlides = imageList.size
                binding.tvPageCount.text = "$currentSlide/$totalSlides"
            }
        })
    }

    private fun catchEvent() {
        binding.btnAddToCart.setOnClickListener {
            startActivity(Intent(this, CartListActivity::class.java))
        }
    }

    override fun openOptionsMenu() {
        super.openOptionsMenu()
        onBackPressedDispatcher.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }
}