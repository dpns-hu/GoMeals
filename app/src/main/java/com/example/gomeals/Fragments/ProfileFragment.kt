package com.example.gomeals.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.gomeals.Models.UserModel
import com.example.gomeals.databinding.FragmentProfileBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ProfileFragment : Fragment() {
   private  lateinit var binding:FragmentProfileBinding

    private lateinit var auth :FirebaseAuth

//    private lateinit var name:String
//    private lateinit var address:String
//    private lateinit var email:String
//    private lateinit var phone:String
//    private lateinit var name:String
//    private lateinit var name:String
//    private lateinit var name:String
//    private lateinit var name:String
//    private lateinit var name:String
//    private lateinit var name:String
//    private lateinit var name:String
//    private lateinit var name:String
//    private lateinit var name:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        auth = FirebaseAuth.getInstance()
        setUserData()
        binding.saveInformation.setOnClickListener{
            val name = binding.profileFragNameEdit.text.toString()
            val email = binding.profileFragEmailEdit.text.toString()
            val address = binding.profileFragAddressEdit.text.toString()
            val phone = binding.profileFragPhoneEdit.text.toString()
            saveInformationToDatabase(name,email,phone,address)
        }
        return binding.root

    }

    private fun saveInformationToDatabase(
        name: String,
        email: String,
        phone: String,
        address: String
    ) {
        val userId = auth.currentUser?.uid?:""
        if(userId!=null){
            val userRef = Firebase.database.reference.child("Users").child(userId)
            val userData = hashMapOf<String,String>(
                "name" to name,
                "email" to email,
                "address" to address,
                "phone" to phone
            )
            userRef.setValue(userData).addOnSuccessListener {
                Toast.makeText(requireContext(),"Information Saved",Toast.LENGTH_SHORT
                ).show()
            }.addOnFailureListener {
                Toast.makeText(requireContext(),"Information not Saved",Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setUserData() {
        val userId = auth.currentUser?.uid?:""
        if(userId!=null) {
            val databaseRef = FirebaseDatabase.getInstance().reference
            val userRef = databaseRef.child("Users").child(userId)
            userRef.addListenerForSingleValueEvent(object:ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        val userProfile = snapshot.getValue(UserModel::class.java)
                        if(userProfile!=null){
                            binding.profileFragNameEdit.setText(userProfile.name)
                            binding.profileFragAddressEdit.setText(userProfile.address)
                            binding.profileFragEmailEdit.setText(userProfile.email)

                            binding.profileFragPhoneEdit.setText(userProfile.phone)

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