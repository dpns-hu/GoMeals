package com.example.gomeals.data.repository

import com.example.gomeals.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(private val auth: FirebaseAuth) :AuthRepository {

    override fun login(email: String, password: String): Flow<Boolean> = flow {
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            emit(true) // Login successful
        } catch (exception: Exception) {
            emit(false) // Login failed
        }
    }.catch { exception ->
        emit(false) // Handle exceptions and emit failure
    }

}