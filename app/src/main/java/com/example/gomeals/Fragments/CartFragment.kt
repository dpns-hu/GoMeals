package com.example.gomeals.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gomeals.DetailsActivity
import com.example.gomeals.PayoutActivity
import com.example.gomeals.R
import com.example.gomeals.adapter.CartListAdapter
import com.example.gomeals.adapter.popularListAdapter
import com.example.gomeals.databinding.FragmentCartBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CartFragment : Fragment() {
     lateinit var binding : FragmentCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentCartBinding.inflate(inflater,container,false)
        val foodName = mutableListOf("abcd","efgh","jklm","mnop","qrst","uvwx","yz")
        val pricelist = mutableListOf("5$", "2$", "3$","100$" ,"1001$","10021$","13001$")
        val imageFood = mutableListOf(R.drawable.banner2,R.drawable.banner3,R.drawable.banner3,R.drawable.banner3,R.drawable.banner3,R.drawable.banner3,R.drawable.banner3)
        val adapter = CartListAdapter(foodName as MutableList<String>,
            pricelist as MutableList<String>, imageFood as MutableList<Int>
        )
        binding.cartItemRV.layoutManager = LinearLayoutManager(requireContext())
        binding.cartItemRV.adapter = adapter
        binding.proceedFrgcartButton.setOnClickListener{
            val intent = Intent(requireContext(),PayoutActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }


}