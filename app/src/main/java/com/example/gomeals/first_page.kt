package com.example.gomeals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gomeals.databinding.ActivityFirstPageBinding

class first_page : AppCompatActivity() {
    lateinit var binding: ActivityFirstPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.firstNextButton.setOnClickListener{
            val intent = Intent(this, Login_page::class.java)
            startActivity(intent)
            finish()
        }

    }
}