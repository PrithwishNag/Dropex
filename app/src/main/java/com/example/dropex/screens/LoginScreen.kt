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

class LoginScreen : AppCompatActivity() {
    private lateinit var registerLinkText: TextView
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var submitBtn: MaterialButton
    private lateinit var mAuth: FirebaseAuth
    private fun authenticate(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task: Task<AuthResult?> ->
                progressBar.visibility = View.GONE
                if (task.isSuccessful) {
                    val intent = Intent(this, HomeScreen::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Failed to login", Toast.LENGTH_SHORT).show()
                }
            }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        registerLinkText = findViewById(R.id.register)
        editTextEmail = findViewById(R.id.editTextUsername)
        editTextPassword = findViewById(R.id.editTextPassword)
        submitBtn = findViewById(R.id.submit)
        progressBar = findViewById(R.id.progressBar)
        mAuth = FirebaseAuth.getInstance()
        registerLinkText.setOnClickListener { view: View? ->
            val intent = Intent(this, RegisterScreen::class.java)
            startActivity(intent)
            finish()
        }
        submitBtn.setOnClickListener(View.OnClickListener {
            progressBar.visibility = View.VISIBLE
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            if (!Utils.validateUserAuthData(this, email, password)) {
                progressBar.visibility = View.GONE
                return@OnClickListener
            }
            authenticate(email, password)
        })
    }
}