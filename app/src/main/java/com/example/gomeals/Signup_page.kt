package com.example.gomeals

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.gomeals.Models.UserModel
import com.example.gomeals.databinding.ActivitySignupPageBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase

class Signup_page : AppCompatActivity() {
    lateinit var binding : ActivitySignupPageBinding
    lateinit var name :String
    lateinit var email :String
    lateinit var password :String
    lateinit var auth : FirebaseAuth
    lateinit var database : FirebaseDatabase
    lateinit var googleSignInClient : GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySignupPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        val signInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()
        googleSignInClient = GoogleSignIn.getClient(this,signInOption)
        binding.Googlesignuppage.setOnClickListener {
            val signInClient = googleSignInClient.signInIntent

            launcher.launch(signInClient)
        }
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
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result->
        if(result.resultCode== Activity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            if(task.isSuccessful){
                val account: GoogleSignInAccount?=task.result
                val credential = GoogleAuthProvider.getCredential(account?.idToken,null)
                auth.signInWithCredential(credential).addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(this,"Sign Up Successful",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()

                    }else{
                        Toast.makeText(this,"Sign Up Failed",Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }else{
            Toast.makeText(this,"Sign Up Failed",Toast.LENGTH_SHORT).show()
        }
    }
}
