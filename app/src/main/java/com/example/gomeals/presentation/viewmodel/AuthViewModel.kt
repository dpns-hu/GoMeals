package com.example.gomeals.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gomeals.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class AuthViewModel @Inject constructor(private val loginUseCase: LoginUseCase):ViewModel() {

    private val _loginResponse  = MutableLiveData<Boolean>()
    var loginResponse = _loginResponse


    fun login(email:String,password:String){
         viewModelScope.launch {
            val result =  loginUseCase.execute(email,password)
             loginResponse.postValue(result)
         }
     }
}