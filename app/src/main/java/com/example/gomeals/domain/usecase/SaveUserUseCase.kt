package com.example.gomeals.domain.usecase

import com.example.gomeals.data.Models.UserModel
import com.example.gomeals.domain.repository.DatabaseRepository
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository)
{
      fun execute(userDetails:UserModel){
          databaseRepository.saveToDatabase(userDetails)
    }
}
