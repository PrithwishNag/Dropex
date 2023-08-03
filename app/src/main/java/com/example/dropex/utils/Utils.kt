package com.example.dropex.utils

import android.content.Context
import android.widget.Toast
import com.example.dropex.constants.Constants
import java.util.regex.Pattern

object Utils {
    fun validateUserAuthData(context: Context?, email: String, password: String): Boolean {
        if (email.isEmpty()) {
            Toast.makeText(context, "Enter Email", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.isEmpty()) {
            Toast.makeText(context, "Enter Password", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!Pattern.matches(Constants.EMAIL_REGEX, email)) {
            Toast.makeText(context, "Invalid Email", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!Pattern.matches(Constants.PASSWORD_REGEX, password)) {
            Toast.makeText(context, "Invalid Password", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}