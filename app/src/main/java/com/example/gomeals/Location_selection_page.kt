package com.example.gomeals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.gomeals.databinding.ActivityLocationSelectionPageBinding

class Location_selection_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var binding : ActivityLocationSelectionPageBinding
        super.onCreate(savedInstanceState)
        binding = ActivityLocationSelectionPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val locationList = arrayOf("Rewa","Hyderabad","Goa","Surat", "Satna", "Jaipur","Jabalpur","Indore","Kerala")
        val adapter = ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,locationList)
            binding.listOfLocation.setAdapter(adapter)
    }
}