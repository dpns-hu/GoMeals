package com.example.gomeals.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.gomeals.R
import com.example.gomeals.adapter.popularListAdapter
import com.example.gomeals.databinding.FragmentHomeBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeFragment : Fragment() {
lateinit var binding : FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        binding.viewMenuText.setOnClickListener {
            val bottomSheet = BottomSheetFragment()
            bottomSheet.show(parentFragmentManager,"TEST")
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.banner2,ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner22,ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner3,ScaleTypes.FIT))
        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList)
        imageSlider.setImageList(imageList,ScaleTypes.FIT)
        val foodName = listOf("abcd","efgh","jklm","mnop","qrst","uvwx","yz")
        val pricelist = listOf("5$", "2$", "3$","100$" ,"1001$","10021$","13001$")
        val imageFood = listOf(R.drawable.banner2,R.drawable.banner3,R.drawable.banner3,R.drawable.banner3,R.drawable.banner3,R.drawable.banner3,R.drawable.banner3)
        val adapter = popularListAdapter(foodName,imageFood,pricelist,requireContext())
        binding.popularListRV.layoutManager = LinearLayoutManager(requireContext())
        binding.popularListRV.adapter = adapter

    }


}



