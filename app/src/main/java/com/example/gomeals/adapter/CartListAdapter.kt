package com.example.gomeals.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gomeals.databinding.CartItemlistBinding

    class CartListAdapter(private val foodList:MutableList<String>,private val priceList:MutableList<String>,
        private val imageList:MutableList<Int>): RecyclerView.Adapter<CartListAdapter.CartViewHolder>() {
private val itemQuantities = IntArray(foodList.size){1}//Initilized every   index value to 1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(CartItemlistBinding.inflate(LayoutInflater.from(parent.context),parent,false ))
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
       holder.bind(position)
    }
    inner class CartViewHolder (private val binding: CartItemlistBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantities[position]
                cartFoodName.text = foodList[position]
                cartPrice.text = priceList[position]
                cartImageView.setImageResource(imageList[position])
                cartQuantityText.text = itemQuantities[position].toString()
                cartAddButton.setOnClickListener {
                    if(itemQuantities[position]<=10){
                        itemQuantities[position]++
                        cartQuantityText.text = itemQuantities[position].toString()
                    }
                }
                cartsubstractButton.setOnClickListener{
                    if(itemQuantities[position]>1){
                        itemQuantities[position]--
                        cartQuantityText.text = itemQuantities[position].toString()
                    }
                }
                cartDeleteButton.setOnClickListener {
                    val itemPosition = adapterPosition
                    if(itemPosition!=RecyclerView.NO_POSITION){
                        foodList.removeAt(itemPosition)
                        imageList.removeAt(itemPosition)
                        priceList.removeAt(itemPosition)
                        notifyItemRemoved(itemPosition)
                        notifyItemRangeChanged(itemPosition ,foodList.size)
                    }

                }
            }
        }

    }


}