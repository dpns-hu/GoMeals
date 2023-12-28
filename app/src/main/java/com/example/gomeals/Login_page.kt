package com.example.gomeals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gomeals.databinding.ActivityLoginPageBinding

class Login_page : AppCompatActivity() {
    lateinit var binding: ActivityLoginPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
     binding.sentsignupLoginpage.setOnClickListener {
         val intent = Intent(this,Signup_page::class.java)
         startActivity(intent)
     }
    }
}