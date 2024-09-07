package com.example.gomeals.domain.usecase

import com.example.gomeals.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {

    suspend fun execute(email: String, password: String): Boolean
    {
        return try {
            // Call the repository's login method and collect the result
            authRepository.login(email, password).first()
        } catch (e: Exception) {
            // Handle any exceptions that might occur
            false
        }
    }
}