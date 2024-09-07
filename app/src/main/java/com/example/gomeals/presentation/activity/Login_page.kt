package com.example.gomeals.presentation.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.gomeals.R
import com.example.gomeals.databinding.ActivityLoginPageBinding
import com.example.gomeals.presentation.viewmodel.AuthViewModel
import com.example.gomeals.util.UiUtil
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Login_page : AppCompatActivity() {

    private lateinit var binding: ActivityLoginPageBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var googleSignInClient: GoogleSignInClient

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, signInOptions)

        binding.googleSignUpPage.setOnClickListener {
            val signInClient = googleSignInClient.signInIntent
            launcher.launch(signInClient)
        }

        binding.sentsignupLoginpage.setOnClickListener {
            val intent = Intent(this, Signup_page::class.java)
            startActivity(intent)
        }

        binding.loginButton.setOnClickListener {
            val email = binding.emailLogin.text.toString().trim()
            val password = binding.passwordLoginn.text.toString().trim()
            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Fill all the details", Toast.LENGTH_SHORT).show()
            } else {
                signInUser(email, password)
            }
        }

        observeViewModel()
    }

    override fun onStart() {
        super.onStart()
        auth.currentUser?.let {
            updateUI()
        }
    }

    private fun observeViewModel() {
        authViewModel.loginResponse.observe(this, Observer { isLoggedIn ->
            if (isLoggedIn) {
                UiUtil.showToast(this, "Login Successful")
                updateUI()
            } else {
                UiUtil.showToast(this, "Login Failed")
            }
        })
    }

    private fun signInUser(email: String, password: String) {
        authViewModel.login(email, password)
    }

    private fun updateUI() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                if (task.isSuccessful) {
                    val account: GoogleSignInAccount? = task.result
                    val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
                    auth.signInWithCredential(credential).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            UiUtil.showToast(this, "Login Successful")
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        } else {
                            UiUtil.showToast(this, "Login Failed")
                        }
                    }
                } else {
                    UiUtil.showToast(this, "Login Failed")
                }
            } else {
                UiUtil.showToast(this, "Login Failed")
            }
        }
}
