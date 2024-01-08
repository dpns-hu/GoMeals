package com.example.gomeals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gomeals.Fragments.SuccessBottomSheet
import com.example.gomeals.databinding.ActivityPayoutBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PayoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPayoutBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    private lateinit var foodName: ArrayList<String>
    private lateinit var foodImage: ArrayList<String>
    private lateinit var foodPrice: ArrayList<String>
    private lateinit var foodDescription: ArrayList<String>
    private lateinit var foodQuantity: ArrayList<Int>
    private lateinit  var totalAmount: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPayoutBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        setUserData()
        val intent =intent
        foodName = intent.getStringArrayListExtra("foodItemName")!!
        foodPrice = intent.getStringArrayListExtra("foodItemPrice")!!
        foodImage = intent.getStringArrayListExtra("foodItemImage")!!
        foodDescription = intent.getStringArrayListExtra("foodItemDescription")!!
        foodQuantity = intent.getIntegerArrayListExtra("foodItemQuantity")!!
          totalAmount = calculateTotalAmount().toString()+"$"
        binding.totalPricePayout.isEnabled= false
        binding.totalPricePayout.setText(totalAmount)

        setContentView(binding.root)
       binding.proceedPayout.setOnClickListener{
           val sheet = SuccessBottomSheet()
           sheet.show(supportFragmentManager,"test")
       }

    }

    private fun calculateTotalAmount(): Int {
        var totalPrice = 0
        for(i in 0 until foodPrice.size) {
            var price = foodPrice[i]
            var lastChar = foodPrice.last()
            var priceIntValue = if(lastChar=="$"){
                price.dropLast(1).toInt()
            }else{
                price.toInt()
            }
            var quantity = foodQuantity[i]
            totalPrice += quantity*priceIntValue
        }
        return totalPrice

    }

    private fun setUserData() {
        val user = auth.currentUser
        if(user!=null){
            val userId = user.uid
            val userRef = database.reference.child("Users").child(userId)
            userRef.addListenerForSingleValueEvent(object:ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        val names = snapshot.child("name").getValue(String::class.java)

                        val addresss = snapshot.child("address").getValue(String::class.java)
                        val phones = snapshot.child("phone").getValue(String::class.java)
                        binding.apply {
                            namePayout.setText(names)
                            addressPayout.setText(addresss)
                            phonePayout.setText(phones)


                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

        }
    }
}