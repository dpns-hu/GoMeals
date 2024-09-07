package com.example.gomeals.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gomeals.domain.usecase.LoginUseCase
import com.example.gomeals.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.processor.internal.definecomponent.codegen._dagger_hilt_components_SingletonComponent
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val signUpUseCase: SignUpUseCase,
):ViewModel() {

    private val _loginResponse  = MutableLiveData<Boolean>()
    var loginResponse = _loginResponse

    private val _signupResponse  = MutableLiveData<Boolean>()
    var signupResponse = _signupResponse
    fun login(email:String,password:String){
         viewModelScope.launch {
            val result =  loginUseCase.execute(email,password)
             loginResponse.postValue(result)
         }
     }

    fun signUp(email:String,password:String){
        viewModelScope.launch {
            val result =  signUpUseCase.execute(email,password)
            signupResponse.postValue(result)
        }
    }
}