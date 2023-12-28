package com.example.gomeals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gomeals.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val foodName = intent.getStringExtra("MenuItemName")
        val imageId = intent.getIntExtra("MenuItemImage",0)
        binding.foodNameDetailsActivity.text = foodName
        binding.imageViewDetailsActivity.setImageResource(imageId)
        binding.backArrowDetailsActivity.setOnClickListener{
           finish()
        }

    }
}