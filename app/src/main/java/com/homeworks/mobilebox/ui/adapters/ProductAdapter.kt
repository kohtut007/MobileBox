package com.homeworks.mobilebox.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.homeworks.mobilebox.R

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var onItemClick: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClick = listener
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.productImage)
        val productName: TextView = itemView.findViewById(R.id.productName)
        val productPrice: TextView = itemView.findViewById(R.id.productPrice)
        val originalPrice: TextView = itemView.findViewById(R.id.originalPrice)
        val discount: TextView = itemView.findViewById(R.id.discount)
        val brandName: TextView = itemView.findViewById(R.id.brandName)
        val stockStatus: TextView = itemView.findViewById(R.id.stockStatus)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product_card_layout, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        // Set sample data for demonstration.
        holder.productName.text = "Redmi Note 10 Pro"
        holder.productPrice.text = "290000 MMK"
//        holder.originalPrice.text = R.string.original_price.toString()
        holder.discount.text = "20% Off"
        holder.brandName.text = "Xiaomi"
        holder.stockStatus.text = "In stock"
        holder.productImage.setImageResource(R.drawable.iv_phone)
    }

    override fun getItemCount(): Int {
        return 4
    }
}
