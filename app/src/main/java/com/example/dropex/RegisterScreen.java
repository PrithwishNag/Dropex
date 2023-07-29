package com.example.dropex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dropex.utils.Utils;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterScreen extends AppCompatActivity {

    TextView loginLinkText;
    EditText editTextEmail;
    EditText editTextPassword;
    ProgressBar progressBar;
    MaterialButton submitBtn;

    FirebaseAuth mAuth;

    private void createUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            progressBar.setVisibility(View.GONE);
            if (task.isSuccessful()) {
                FirebaseUser user = mAuth.getCurrentUser();

                Toast.makeText(this, "Account created", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to create account", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        loginLinkText = findViewById(R.id.login);
        editTextEmail = findViewById(R.id.editTextUsername);
        submitBtn = findViewById(R.id.submit);
        editTextPassword = findViewById(R.id.editTextPassword);
        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        loginLinkText.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginScreen.class);
            startActivity(intent);
            finish();
        });

        submitBtn.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);

            String email = String.valueOf(editTextEmail.getText());
            String password = String.valueOf(editTextPassword.getText());

            if(!Utils.validateUserAuthData(this, email, password)) return;

            createUser(email, password);
        });
    }
}