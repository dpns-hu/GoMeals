package com.example.gomeals.data.repository

import android.util.Log
import com.example.gomeals.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(private val auth: FirebaseAuth) :AuthRepository {

    override fun login(email: String, password: String): Flow<Boolean> = flow {
        // This will emit true only if the login is successful
        auth.signInWithEmailAndPassword(email, password).await()
        Log.d("AuthRepository","Login successful")
        emit(true) // Login successful
    }.catch { exception ->
        // Handle errors here
        Log.d("AuthRepository","Login Failed: ${exception.message}")
        emit(false) // Login failed
    }

}