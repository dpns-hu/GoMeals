package com.example.gomeals.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.gomeals.databinding.HistoryRvDesignBinding
import com.example.gomeals.databinding.ViewMenuListBinding

class HistoryAdapter(private val foodName: MutableList<String>,private val foodPrice:MutableList<String>,private val foodImage:MutableList<Int>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    inner class HistoryViewHolder(private var binding: HistoryRvDesignBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
             binding.apply {
                 historyFoodNameRV.text = foodName[position]
                 historyImageView.setImageResource(foodImage[position])
                 historyFoodPriceRV.text = foodPrice[position]

             }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(HistoryRvDesignBinding.inflate(LayoutInflater.from(parent.context),parent,false    ))
    }

    override fun getItemCount(): Int {
           return foodName.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
       holder.bind(position)
    }
}