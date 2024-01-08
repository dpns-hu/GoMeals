package com.example.gomeals

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.gomeals.Models.cartItemsModel
import com.example.gomeals.databinding.ActivityDetailsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var auth : FirebaseAuth

     var foodName :String?=null
     var foodPrice :String?=null
     var foodDescription :String?=null
     var foodIngredient :String?=null
     var foodImage :String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        binding.addToCartButton.setOnClickListener{
            addToCartItems()
        }

        foodName = intent.getStringExtra("MenuItemName").toString()
        foodPrice = intent.getStringExtra("MenuItemPrice").toString()
        foodDescription = intent.getStringExtra("MenuItemDescription").toString()
        foodIngredient = intent.getStringExtra("MenuItemIngredient").toString()
        foodImage = intent.getStringExtra("MenuItemImage").toString()
        val imageUri = Uri.parse(foodImage)
        binding.foodNameDetailsActivity.text = foodName
        Glide.with(this).load(imageUri).into(binding.imageViewDetailsActivity)
        binding.shortDescription.text = foodDescription
        binding.ingredients.text = foodIngredient
        binding.backArrowDetailsActivity.setOnClickListener{
           finish()
        }


    }
    private fun addToCartItems() {
      val userId = auth.currentUser?.uid?:""
        val cartItems = cartItemsModel(foodName,foodPrice,foodDescription,foodImage,1)
        val database = FirebaseDatabase.getInstance().reference
         database.child("Users").child(userId).child("cartItems").push().setValue(cartItems).addOnSuccessListener {
             Toast.makeText(this,"Items Added in Cart",Toast.LENGTH_SHORT).show()
         }.addOnFailureListener {
             Toast.makeText(this,"Items Failed To Add In Cart",Toast.LENGTH_SHORT).show()
         }
    }
}