package com.example.myapplication

import android.content.Context
import android.widget.Toast

object utils {

    fun showToast(context: Context,message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


}