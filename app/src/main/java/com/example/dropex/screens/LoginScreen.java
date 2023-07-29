package com.example.dropex.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dropex.R;
import com.example.dropex.utils.Utils;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreen extends AppCompatActivity {

    TextView registerLinkText;
    EditText editTextEmail;
    EditText editTextPassword;
    ProgressBar progressBar;
    MaterialButton submitBtn;

    FirebaseAuth mAuth;

    private void authenticate(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            progressBar.setVisibility(View.GONE);
            if (task.isSuccessful()) {
                Intent intent = new Intent(this, HomeScreen.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Failed to login", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(this, HomeScreen.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        registerLinkText = findViewById(R.id.register);
        editTextEmail = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        submitBtn = findViewById(R.id.submit);
        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        registerLinkText.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterScreen.class);
            startActivity(intent);
            finish();
        });

        submitBtn.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);

            String email = String.valueOf(editTextEmail.getText());
            String password = String.valueOf(editTextPassword.getText());

            if(!Utils.validateUserAuthData(this, email, password)) return;

            authenticate(email, password);
        });
    }
}