package com.example.gomeals.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gomeals.data.Models.UserModel
import com.example.gomeals.domain.usecase.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DatabaseViewModel @Inject constructor(
    private val saveUserUseCase: SaveUserUseCase) :ViewModel()
{

    fun saveUserDetails(userDetails:UserModel){
        viewModelScope.launch {
            saveUserUseCase.execute(userDetails)
        }
    }
}