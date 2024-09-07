package com.example.gomeals.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gomeals.presentation.activity.MainActivity
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
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
        return binding.root

    }


}