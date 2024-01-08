package com.example.gomeals.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gomeals.databinding.CartItemlistBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CartListAdapter(private val context: Context, private val foodList:MutableList<String>, private val priceList:MutableList<String>,
                      private val imageList:MutableList<String>,private val foodDescription:MutableList<String>
,private val itemQuantity:MutableList<Int>): RecyclerView.Adapter<CartListAdapter.CartViewHolder>() {
    val auth = FirebaseAuth.getInstance()

    init {
        val database = FirebaseDatabase.getInstance()
        val userId = auth.currentUser?.uid ?: ""
        val cartItemNumbers = foodList.size
        itemQuantities = itemQuantity
        cartItemRef = database.reference.child("Users").child(userId).child("cartItems")
    }

    companion object {
        fun getUpdatedQuantity(): MutableList<Int> {
            val updatedQuantity = mutableListOf<Int>()
            updatedQuantity.addAll(itemQuantities)
            return updatedQuantity
        }


        var itemQuantities: MutableList<Int> = mutableListOf()
        private lateinit var cartItemRef: DatabaseReference
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(
            CartItemlistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class CartViewHolder(private val binding: CartItemlistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantities[position]
                cartFoodName.text = foodList[position]
                cartPrice.text = priceList[position]
                val uriString = Uri.parse(imageList[position])
                Glide.with(context).load(uriString).into(cartImageView)
                cartQuantityText.text = itemQuantities[position].toString()
                cartAddButton.setOnClickListener {
                    if (itemQuantities[position] <= 10) {
                        itemQuantities[position]++
                        cartQuantityText.text = itemQuantities[position].toString()
                    }
                }
                cartsubstractButton.setOnClickListener {
                    if (itemQuantities[position] > 1) {
                        itemQuantities[position]--
                        cartQuantityText.text = itemQuantities[position].toString()
                    }
                }
                cartDeleteButton.setOnClickListener {
                    val posRetrieve = position
                    getUniqueKeyAtPosition(posRetrieve) { uniqueKey ->
                        if (uniqueKey != null) {
                            removeItem(position, uniqueKey)
                        }
                    }
                }

            }
        }
    }

    private fun removeItem(position: Int, uniqueKey: String) {
        if (uniqueKey != null) {
            cartItemRef.child(uniqueKey).removeValue().addOnSuccessListener {
                   foodList.removeAt(position)
                   imageList.removeAt(position)
                   priceList.removeAt(position)
                   foodDescription.removeAt(position)
                   itemQuantity.removeAt(position)
                Toast.makeText(context,"Item Removed",Toast.LENGTH_SHORT).show()
                // update quantities
                itemQuantities = itemQuantities.filterIndexed{index, i -> index!=position }.toMutableList()
                notifyItemRemoved(position)
                notifyItemRangeChanged(position,foodList.size)
            }.addOnFailureListener {
                Toast.makeText(context,"Failed to remove",Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun getUniqueKeyAtPosition(posRetrieve: Int, onComplete: (String?) -> Unit) {
        cartItemRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var uniqueKey: String? = null
                snapshot.children.forEachIndexed { index, dataSnapshot ->
                    if (index == posRetrieve) {
                        uniqueKey = dataSnapshot.key
                        return@forEachIndexed
                    }
                }
                onComplete(uniqueKey)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}




