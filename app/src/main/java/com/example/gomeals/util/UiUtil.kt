package com.example.gomeals.util

import android.content.Context
import android.widget.Toast

object UiUtil {

    fun showToast(context: Context, mes:String){
        Toast.makeText(context,mes,Toast.LENGTH_SHORT).show()
    }
}