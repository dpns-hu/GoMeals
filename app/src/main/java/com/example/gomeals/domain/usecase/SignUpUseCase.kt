package com.example.gomeals.domain.usecase

import android.util.Log
import com.example.gomeals.domain.repository.AuthRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend fun execute(email: String, password: String): Boolean
    {
        return try {
            // Call the repository's login method and collect the result
            authRepository.signUp(email, password).first()
        } catch (e: Exception) {
            Log.d("SignUpUseCase" ,"$e")
             false
        }
    }
}
