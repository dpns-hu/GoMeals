package com.example.gomeals

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.gomeals.databinding.ActivityLoginPageBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase


class Login_page : AppCompatActivity() {
    lateinit var binding: ActivityLoginPageBinding
      lateinit var name :String
     lateinit var email :String
     lateinit var password :String
     lateinit var auth : FirebaseAuth
     lateinit var database : FirebaseDatabase
     lateinit var googleSignInClient :GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

          val signInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()
            googleSignInClient = GoogleSignIn.getClient(this,signInOption)
        binding.googleSignUpPage.setOnClickListener {
            val signInClient = googleSignInClient.signInIntent
            launcher.launch(signInClient)
        }
     binding.sentsignupLoginpage.setOnClickListener {
         val intent = Intent(this,Signup_page::class.java)
         startActivity(intent)
     }
        binding.loginButton.setOnClickListener{
            email = binding.emailLogin.text.toString().trim()
            password = binding.passwordLoginn.text.toString().trim()
            if(email.isBlank() || password.isBlank()){
                Toast.makeText(this,"Fill all the details",Toast.LENGTH_SHORT).show()
            }
            else{
                signInUser(email,password)
            }
        }
    }

    override fun onStart() {
        super.onStart()
         val currentUser = auth.currentUser
        if(currentUser!=null){
            updateUI()
        }

    }

    private fun signInUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {task->
            if(task.isSuccessful){
                Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show()
                updateUI()
            }else{
                Toast.makeText(this,"Login Failed",Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun updateUI() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result->
            if(result.resultCode==Activity.RESULT_OK){
                      val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                if(task.isSuccessful){
                    val account:GoogleSignInAccount?=task.result
                    val credential = GoogleAuthProvider.getCredential(account?.idToken,null)
                    auth.signInWithCredential(credential).addOnCompleteListener {
                        if(it.isSuccessful){
                            Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,MainActivity::class.java))
                            finish()

                        }else{
                            Toast.makeText(this,"Login Failed",Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            }else{
                Toast.makeText(this,"Login Failed",Toast.LENGTH_SHORT).show()
            }
    }

}