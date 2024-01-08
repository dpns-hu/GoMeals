package com.example.gomeals.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gomeals.Models.menuModel
import com.example.gomeals.adapter.MenuListAdapter
import com.example.gomeals.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.math.log


class BottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentBottomSheetBinding
    private lateinit var database:FirebaseDatabase
    private lateinit var menuItems:MutableList<menuModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
         database = FirebaseDatabase.getInstance()
         fetchDataFromDatabase()

        binding.viewMenuBackArrow.setOnClickListener {
            dismiss()
        }

        return binding.root
    }

    private fun fetchDataFromDatabase() {
      val foodRef = database.reference.child("Menu")
        menuItems = mutableListOf()
        foodRef.addListenerForSingleValueEvent(object:ValueEventListener{
            @SuppressLint("SuspiciousIndentation")
            override fun onDataChange(snapshot: DataSnapshot) {
                menuItems.clear()
                for(foodSnapshot in snapshot.children){
                    val Items = foodSnapshot.getValue(menuModel::class.java)
                       menuItems.add(Items!!)

                }

               setAdapter()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun setAdapter() {
        Log.d("giveme","yes ${menuItems[0].foodName}")
        val adapter = MenuListAdapter(menuItems,requireContext())

        binding.viewMenuRV.layoutManager = LinearLayoutManager(requireContext())
        binding.viewMenuRV.adapter = adapter
    }

    companion object {

    }
}

