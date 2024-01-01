package com.example.gomeals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.gomeals.Models.UserModel
import com.example.gomeals.databinding.ActivitySignupPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Signup_page : AppCompatActivity() {
    lateinit var binding : ActivitySignupPageBinding
    lateinit var name :String
    lateinit var email :String
    lateinit var password :String
    lateinit var auth : FirebaseAuth
    lateinit var database : FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySignupPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        binding.HaveanaccountSignupPage.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
binding.signupButton.setOnClickListener{
    name = binding.nameSignUp.text.toString()
    email = binding.emailSignUp.text.toString().trim()
    password = binding.passwordSignUp.text.toString().trim()
    if(name.isBlank() || email.isBlank()|| password.isBlank()){
        Toast.makeText(this,"Fill all the details",Toast.LENGTH_SHORT).show()

    }else{
        signUpUser(email,password)
    }
}
    }
    // check if user already Login


    private fun signUpUser(email: String, password: String) {
         auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
             if(it.isSuccessful){
                 Toast.makeText(this,"Account Creation Successful",Toast.LENGTH_SHORT).show()
                 saveDataInDatabase()
                     // for intent
                 updateUi()
             }else{
                 Toast.makeText(this,"Account Creation failed",Toast.LENGTH_SHORT).show()
                 Log.d("Account","Creation Failed",it.exception)
             }
         }
    }

    private fun saveDataInDatabase() {
        name = binding.nameSignUp.text.toString().trim()
        email = binding.emailSignUp.text.toString().trim()
        password = binding.passwordSignUp.text.toString().trim()

        val user = UserModel(name,email,password)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        if (userId != null) {
            database.reference.child("Users").child(userId).setValue(user)
        } else {
            // Handle the case where currentUser is null
            Log.e("SaveData", "User ID is null")
        }
    }

    private fun updateUi() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
