package com.example.dropex.utils;

import static com.example.dropex.constants.Constants.EMAIL_REGEX;
import static com.example.dropex.constants.Constants.PASSWORD_REGEX;

import android.content.Context;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Utils {

    public static boolean validateUserAuthData(Context context, String email, String password) {
        if (email.isEmpty()) {
            Toast.makeText(context, "Enter Email", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.isEmpty()) {
            Toast.makeText(context, "Enter Password", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Pattern.matches(EMAIL_REGEX, email)) {
            Toast.makeText(context, "Invalid Email", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Pattern.matches(PASSWORD_REGEX, password)) {
            Toast.makeText(context, "Invalid Password", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
