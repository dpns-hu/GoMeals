package com.example.gomeals.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gomeals.DetailsActivity
import com.example.gomeals.Models.cartItemsModel
import com.example.gomeals.PayoutActivity
import com.example.gomeals.R
import com.example.gomeals.adapter.CartListAdapter
import com.example.gomeals.adapter.popularListAdapter
import com.example.gomeals.databinding.FragmentCartBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CartFragment : Fragment() {
     lateinit var binding : FragmentCartBinding
      private lateinit var foodList:MutableList<String>
      private lateinit var priceList:MutableList<String>
    private lateinit var imageList:MutableList<String>
    private lateinit  var foodDescription:MutableList<String>
private lateinit var userId : String
    private lateinit var itemQuantity:MutableList<Int>
    private lateinit var auth:FirebaseAuth
    private lateinit var database:FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentCartBinding.inflate(inflater,container,false)
         auth = FirebaseAuth.getInstance()
        fetchDataFromDatabase()


        binding.proceedFrgcartButton.setOnClickListener{
            sentDataToPayOut()

        }
        return binding.root
    }

    private fun sentDataToPayOut() {
        val foodRef = database.reference.child("Users").child(userId).child("cartItems")
       var foodName = mutableListOf<String>()
       var  foodPrice = mutableListOf<String>()
       var foodImage = mutableListOf<String>()
       var  foodDescription = mutableListOf<String>()
       var itemQuantity  = CartListAdapter.getUpdatedQuantity()
        foodRef.addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (foodSnapshot in snapshot.children) {
                    val items = foodSnapshot.getValue(cartItemsModel::class.java)
                    items?.foodName?.let { foodName.add(it) }
                    items?.foodPrice?.let { foodPrice.add(it) }
                    items?.foodImage?.let { foodImage.add(it) }
                    items?.foodDescription?.let { foodDescription.add(it) }

                }
                orderNow(foodName,foodImage,foodPrice,foodDescription,itemQuantity)
            }



            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(),"Failed to make order",Toast.LENGTH_SHORT).show()
            }

        })


    }
    private fun orderNow(foodName: MutableList<String>, foodImage: MutableList<String>, foodPrice: MutableList<String>, foodDescription: MutableList<String>, itemQuantity: MutableList<Int>) {
        if (isAdded && context != null) {
            val intent = Intent(requireContext(),PayoutActivity::class.java)
            intent.putExtra("foodItemName",foodName as ArrayList<String>)
            intent.putExtra("foodItemPrice",foodPrice as ArrayList<String>)
            intent.putExtra("foodItemImage",foodImage as ArrayList<String>)
            intent.putExtra("foodItemDescription",foodDescription as ArrayList<String>)
            intent.putExtra("foodItemQuantity",itemQuantity as ArrayList<Int>)
            startActivity(intent)


        }
    }
    private fun fetchDataFromDatabase() {
          database = FirebaseDatabase.getInstance()
          userId = auth.currentUser?.uid?:""
        val cartRef = database.reference.child("Users").child(userId).child("cartItems")
        foodList = mutableListOf()
        imageList = mutableListOf()
        priceList = mutableListOf()
        foodDescription = mutableListOf()

        itemQuantity = mutableListOf()

        cartRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(foodSnapshot in snapshot.children){
                    val items = foodSnapshot.getValue(cartItemsModel::class.java)
                    items?.foodName?.let { foodList.add(it) }
                    items?.foodImage?.let { imageList.add(it) }
                    items?.foodPrice?.let { priceList.add(it) }
                    items?.foodDescription?.let { foodDescription.add(it) }
                    items?.foodQuantity?.let { itemQuantity.add(it) }
                }
                cartAdapter()
            }

            override fun onCancelled(error: DatabaseError) {
               Toast.makeText(requireContext(),"Failed to fetch data",Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun cartAdapter() {
        val adapter = CartListAdapter(requireContext(),foodList,priceList,imageList,foodDescription,itemQuantity
        )
        binding.cartItemRV.layoutManager = LinearLayoutManager(requireContext())
        binding.cartItemRV.adapter = adapter
    }


}




