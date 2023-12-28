package com.example.gomeals.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.gomeals.DetailsActivity
import com.example.gomeals.databinding.PopularItemlistBinding

class popularListAdapter(private val items:List<String>,private val images:List<Int>,private val prices:List<String>
,private val requireContext:Context): RecyclerView.Adapter<popularListAdapter.PopularViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
            return PopularViewHolder(PopularItemlistBinding.inflate(LayoutInflater.from(parent.context),parent,false    ))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
         val item = items[position]
        val image = images[position]
        val price = prices[position]

        holder.bind(item,image,price)
        holder.itemView.setOnClickListener{
            val intent = Intent(requireContext, DetailsActivity::class.java)
            intent.putExtra("MenuItemName",item )
            intent.putExtra("MenuItemImage",image )
            requireContext.startActivity(intent)
        }
    }
    class PopularViewHolder(private val binding: PopularItemlistBinding):RecyclerView.ViewHolder(binding.root) {
        private val imageView = binding.imagePopularList
        fun bind(item: String, image: Int,price:String) {
         binding.foodNamePopular.text = item
            binding.pricePopular.text = price
            imageView.setImageResource(image)


        }

    }
}