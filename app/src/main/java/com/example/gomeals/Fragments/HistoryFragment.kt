package com.example.gomeals.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gomeals.R
import com.example.gomeals.adapter.HistoryAdapter
import com.example.gomeals.databinding.FragmentHistoryBinding




class HistoryFragment : Fragment() {
   private lateinit var binding: FragmentHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater,container,false)
        var foodName = mutableListOf<String>("abcd","efgh","jklm","mnop","qrst","uvwx","yz")
        var foodPrice = mutableListOf<String>("5$", "2$", "3$","100$" ,"1001$","10021$","13001$")
        var foodImage = mutableListOf<Int>(R.drawable.banner2,R.drawable.banner3,R.drawable.banner3,R.drawable.banner3,R.drawable.banner3,R.drawable.banner3,R.drawable.banner3)
         val adapter = HistoryAdapter(foodName,foodPrice,foodImage)
        binding.historyRV.layoutManager = LinearLayoutManager(requireContext())
        binding.historyRV.adapter = adapter
        return binding.root
    }


}