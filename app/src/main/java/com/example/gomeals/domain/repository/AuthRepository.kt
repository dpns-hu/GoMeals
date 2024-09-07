package com.example.gomeals.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(email:String,password:String): Flow<Boolean>
}