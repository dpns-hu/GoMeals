package com.example.gomeals.data.repository

import android.util.Log
import com.example.gomeals.data.Models.UserModel
import com.example.gomeals.domain.repository.DatabaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class DatabaseRepositoryImpl(private val database: FirebaseDatabase) : DatabaseRepository {


    override fun saveToDatabase(userDetails: UserModel) {
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        if (userId != null) {
            database.reference.child("Users").child(userId).setValue(userDetails)
            Log.e("SaveData", "User Details Saved")

        } else {
            // Handle the case where currentUser is null
            Log.e("SaveData", "User ID is null")
        }
      }
}