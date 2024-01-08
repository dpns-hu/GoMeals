package com.example.gomeals.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.gomeals.Models.menuModel
import com.example.gomeals.R
import com.example.gomeals.adapter.MenuListAdapter
import com.example.gomeals.databinding.FragmentHomeBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeFragment : Fragment() {
lateinit var binding : FragmentHomeBinding
lateinit var database : FirebaseDatabase
lateinit var menuItems: MutableList<menuModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        database = FirebaseDatabase.getInstance()
        fetchDataFromDatabase()
        binding.viewMenuText.setOnClickListener {
            val bottomSheet = BottomSheetFragment()
            bottomSheet.show(parentFragmentManager,"TEST")
        }
        return binding.root

    }

    private fun fetchDataFromDatabase() {
        val foodref = database.reference.child("Menu")
        menuItems =  mutableListOf()
        foodref.addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(foodSnapshot in snapshot.children){
                    val items = foodSnapshot.getValue(menuModel::class.java)
                    menuItems.add(items!!)
                }
                ramdomPopularItems()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun ramdomPopularItems() {
        val shuffledList = menuItems.indices.toList().shuffled()
        val numToShow = 6
        val subsetShuffledList = shuffledList.take(6).map{menuItems[it]}
        setAdapter(subsetShuffledList)
    }

    private fun setAdapter(subsetShuffledList: List<menuModel>) {
        val adapter = MenuListAdapter(subsetShuffledList,requireContext())
        binding.popularListRV.layoutManager = LinearLayoutManager(requireContext())
        binding.popularListRV.adapter = adapter
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

    }


}



