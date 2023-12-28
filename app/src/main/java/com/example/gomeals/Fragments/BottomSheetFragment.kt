package com.example.gomeals.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gomeals.R
import com.example.gomeals.adapter.CartListAdapter
import com.example.gomeals.adapter.MenuListAdapter
import com.example.gomeals.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentBottomSheetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        val foodName = mutableListOf("abcd", "efgh", "jklm", "mnop", "qrst", "uvwx", "yz")
        val pricelist = mutableListOf("5$", "2$", "3$", "100$", "1001$", "10021$", "13001$")
        val imageFood = mutableListOf(
            R.drawable.banner2,
            R.drawable.banner3,
            R.drawable.banner3,
            R.drawable.banner3,
            R.drawable.banner3,
            R.drawable.banner3,
            R.drawable.banner3
        )
        val adapter = MenuListAdapter(
            foodName as MutableList<String>, imageFood as MutableList<Int>,
            pricelist as MutableList<String>,requireContext()
        )
        binding.viewMenuBackArrow.setOnClickListener {
            dismiss()
        }
        binding.viewMenuRV.layoutManager = LinearLayoutManager(requireContext())
        binding.viewMenuRV.adapter = adapter
        return binding.root
    }

    companion object {

    }
}

