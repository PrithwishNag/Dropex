package com.example.dropex.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dropex.R
import com.example.dropex.utils.Utils
import com.google.android.gms.tasks.Task
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class RegisterScreen : AppCompatActivity() {
    private lateinit var loginLinkText: TextView
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var submitBtn: MaterialButton
    private lateinit var mAuth: FirebaseAuth
    private fun createUser(email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task: Task<AuthResult?> ->
                progressBar.visibility = View.GONE
                if (task.isSuccessful) {
                    Toast.makeText(this, "Account created", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Failed to create account", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_screen)
        loginLinkText = findViewById(R.id.login)
        editTextEmail = findViewById(R.id.editTextUsername)
        editTextPassword = findViewById(R.id.editTextPassword)
        submitBtn = findViewById(R.id.submit)
        progressBar = findViewById(R.id.progressBar)
        mAuth = FirebaseAuth.getInstance()
        loginLinkText.setOnClickListener { view: View? ->
            val intent = Intent(this, LoginScreen::class.java)
            startActivity(intent)
            finish()
        }
        submitBtn.setOnClickListener(View.OnClickListener { view: View? ->
            progressBar.visibility = View.VISIBLE
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            if (!Utils.validateUserAuthData(this, email, password)) return@OnClickListener
            createUser(email, password)
        })
    }
}