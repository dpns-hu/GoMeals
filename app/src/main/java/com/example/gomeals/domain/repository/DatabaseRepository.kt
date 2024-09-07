package com.example.gomeals.domain.repository

import com.example.gomeals.data.Models.UserModel

interface DatabaseRepository {
    fun saveToDatabase(userDetails:UserModel)
}