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
import com.example.dropex.database.UserRepository
import com.example.dropex.models.UserModel
import com.example.dropex.utils.Utils
import com.google.android.gms.tasks.Task
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class RegisterScreen : AppCompatActivity() {
    private lateinit var loginLinkText: TextView
    private lateinit var emailET: EditText
    private lateinit var nameET: EditText
    private lateinit var addressET: EditText
    private lateinit var passwordET: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var submitBtn: MaterialButton
    private lateinit var mAuth: FirebaseAuth
    private var userRepository: UserRepository = UserRepository()
    private fun createUser(userModel: UserModel, password: String) {
        mAuth.createUserWithEmailAndPassword(userModel.email, password).addOnFailureListener(this) {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                }.addOnCompleteListener(this) { task: Task<AuthResult?> ->
                    progressBar.visibility = View.GONE
                    if (task.isSuccessful) {
                        userRepository.createNewUser(mUser = mAuth.currentUser!!, user = userModel)
                        Toast.makeText(this, "Account created", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Failed to create account", Toast.LENGTH_SHORT).show()
                    }
                }
    }

    private fun submit() {
        progressBar.visibility = View.VISIBLE
        val email = emailET.text.toString()
        val name = nameET.text.toString()
        val address = addressET.text.toString()
        val password = passwordET.text.toString()
        val user = UserModel(email = email, name = name, address = address)
        if (!Utils.validateUserAuthData(this, email, password)) {
            progressBar.visibility = View.GONE
            return
        }
        createUser(user, password)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_screen)
        loginLinkText = findViewById(R.id.login)
        emailET = findViewById(R.id.usernameET)
        nameET = findViewById(R.id.nameET)
        addressET = findViewById(R.id.addressET)
        passwordET = findViewById(R.id.passwordET)
        submitBtn = findViewById(R.id.submit)
        progressBar = findViewById(R.id.progressBar)
        mAuth = FirebaseAuth.getInstance()
        loginLinkText.setOnClickListener { view: View? ->
            val intent = Intent(this, LoginScreen::class.java)
            startActivity(intent)
            finish()
        }
        submitBtn.setOnClickListener { submit() }
    }
}