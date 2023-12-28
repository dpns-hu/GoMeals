package com.example.gomeals.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gomeals.R
import com.example.gomeals.adapter.notification_adapter
import com.example.gomeals.databinding.FragmentNotificationBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class notification_bottom_sheet : BottomSheetDialogFragment() {

private lateinit var binding: FragmentNotificationBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationBottomSheetBinding.inflate(layoutInflater,container,false)
        val notification = listOf<String>("order placed successfully","order has been cancelled", "order reached")
        val imageNotification = listOf<Int>(R.drawable.done_completed,R.drawable.sad_emoji,R.drawable.bus_image)
        val adapter = notification_adapter(ArrayList(notification), ArrayList(imageNotification))
        binding.rvNotificationSheet.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNotificationSheet.adapter = adapter
        return binding.root
    }


    }
