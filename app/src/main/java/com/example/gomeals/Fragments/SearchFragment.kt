package com.example.gomeals.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gomeals.R
import com.example.gomeals.adapter.CartListAdapter
import com.example.gomeals.adapter.MenuListAdapter
import com.example.gomeals.databinding.FragmentSearchBinding



class SearchFragment : Fragment() {
  private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter:MenuListAdapter
    var foodName = mutableListOf<String>("abcd","efgh","jklm","mnop","qrst","uvwx","yz")
    var foodPrice = mutableListOf<String>("5$", "2$", "3$","100$" ,"1001$","10021$","13001$")
    var foodImage = mutableListOf<Int>(R.drawable.banner2,R.drawable.banner3,R.drawable.banner3,R.drawable.banner3,R.drawable.banner3,R.drawable.banner3,R.drawable.banner3)
    private var filterFoodName = mutableListOf<String>()
    private var filterFoodPrice = mutableListOf<String>()
    private var filterFoodImage = mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater,container,false)


        adapter = MenuListAdapter(filterFoodName,filterFoodImage,filterFoodPrice,requireContext()   )
        binding.SearchRV.layoutManager = LinearLayoutManager(requireContext())
        binding.SearchRV.adapter = adapter
           codeForSearchView()
        showAllMenuItems()
        return binding.root
    }

    private fun showAllMenuItems() {
        filterFoodPrice.clear()
        filterFoodName.clear()
        filterFoodImage.clear()
        filterFoodImage.addAll(foodImage)
        filterFoodName.addAll(foodName)
        filterFoodPrice.addAll(foodPrice)
        adapter.notifyDataSetChanged()
    }

    private fun codeForSearchView() {
         binding.searchViewSearch.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
             override fun onQueryTextSubmit(query: String): Boolean {
                 filterMenu(query)
                 return true
             }

             override fun onQueryTextChange(newText: String): Boolean {
                filterMenu( newText)
                 return true

             }


         })
    }

    private fun filterMenu(query: String) {
     filterFoodPrice.clear()
        filterFoodName.clear()
        filterFoodImage.clear()
    foodName.forEachIndexed{index,foodN->
        if(foodN.contains(query.toString(),ignoreCase = true)){
            filterFoodImage.add(foodImage[index])
            filterFoodName.add(foodN)
            filterFoodPrice.add(foodPrice[index])

        }
    }
    adapter.notifyDataSetChanged()}



}