package com.example.gomeals.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gomeals.DetailsActivity
import com.example.gomeals.Models.menuModel
import com.example.gomeals.databinding.ViewMenuListBinding

class MenuListAdapter(private val menuItems: List<menuModel>, private val requireContext:Context):RecyclerView.Adapter<MenuListAdapter.MenuViewHolder>() {

    inner class MenuViewHolder(private val binding:ViewMenuListBinding):RecyclerView.ViewHolder(binding.root) {
        init{
            binding.root.setOnClickListener{
                val pos = adapterPosition
                if(pos!=RecyclerView.NO_POSITION){
                   openDetailActivity(pos)
                }

            }
        }
        fun bind(position: Int) {
            binding.apply{
                foodNameMenu.text = menuItems[position].foodName
                priceMenu.text = menuItems[position].foodPrice
                val imageUri = Uri.parse(menuItems[position].foodImageUri)
                Glide.with(requireContext).load(imageUri).into(imageMenuList)

            }

        }

    }

    private fun openDetailActivity(pos: Int) {
        val intent = Intent(requireContext, DetailsActivity::class.java)
        intent.putExtra("MenuItemName", menuItems[pos].foodName)
        intent.putExtra("MenuItemImage", menuItems[pos].foodImageUri)
        intent.putExtra("MenuItemDescription", menuItems[pos].foodDescription)
        intent.putExtra("MenuItemPrice", menuItems[pos].foodPrice)
        intent.putExtra("MenuItemIngredient", menuItems[pos].foodIngredient)
        requireContext.startActivity(intent)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(ViewMenuListBinding.inflate(LayoutInflater.from(parent.context),parent,false    ))
    }

    override fun getItemCount(): Int {
        return menuItems.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(position)
    }
    interface  OnClickListener{
        fun onItemClick(pos: Int)
    }
}





