package com.example.gomeals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gomeals.Fragments.SuccessBottomSheet
import com.example.gomeals.databinding.ActivityPayoutBinding

class PayoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPayoutBinding.inflate(layoutInflater)

        setContentView(binding.root)
       binding.proceedPayoutActivity.setOnClickListener{
           val sheet = SuccessBottomSheet()
           sheet.show(supportFragmentManager,"test")
       }

    }
}