package com.example.gomeals.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gomeals.DetailsActivity
import com.example.gomeals.databinding.ViewMenuListBinding

class MenuListAdapter(private val items:MutableList<String>,private val images:MutableList<Int>,private val prices:MutableList<String>,
private val requireContext:Context):RecyclerView.Adapter<MenuListAdapter.MenuViewHolder>() {
    val itemClickListener:OnClickListener?=null
    inner class MenuViewHolder(private val binding:ViewMenuListBinding):RecyclerView.ViewHolder(binding.root) {
        init{
            binding.root.setOnClickListener{
                val pos = adapterPosition
                if(pos!=RecyclerView.NO_POSITION){
                    itemClickListener?.onItemClick(pos)
                }
                val intent = Intent(requireContext,DetailsActivity::class.java)
                intent.putExtra("MenuItemName",items[pos])
                intent.putExtra("MenuItemImage",images[pos])
                requireContext.startActivity(intent)
            }
        }
        fun bind(position: Int) {
            binding.apply{
                foodNameMenu.text = items[position]
                priceMenu.text = prices[position]
                imageMenuList.setImageResource(images[position])

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(ViewMenuListBinding.inflate(LayoutInflater.from(parent.context),parent,false    ))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(position)
    }
    interface  OnClickListener{
        fun onItemClick(pos: Int)
    }
}





