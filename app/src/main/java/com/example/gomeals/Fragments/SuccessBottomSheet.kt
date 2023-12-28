package com.example.gomeals.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gomeals.MainActivity
import com.example.gomeals.databinding.FragmentNotificationBottomSheetBinding
import com.example.gomeals.databinding.FragmentSuccessBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class SuccessBottomSheet : BottomSheetDialogFragment() {
  lateinit var binding: FragmentSuccessBottomSheetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentSuccessBottomSheetBinding.inflate(layoutInflater,container,false)

        binding.successFrgGoHomeButton.setOnClickListener{
            val intent = Intent(requireContext(),MainActivity::class.java)
            startActivity(intent)
        }
        return binding.root

    }


}