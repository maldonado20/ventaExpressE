package com.example.ventaexpresse

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvRedirectSignUp: TextView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etEmail = findViewById(R.id.editTextTextEmailAddress)
        etPass = findViewById(R.id.editTextTextPassword)
        btnLogin = findViewById(R.id.button_login)
        tvRedirectSignUp = findViewById(R.id.tvRedirectSignUp)

        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener { login() }

        tvRedirectSignUp.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }

    private fun login() {
        val email = etEmail.text.toString().trim()
        val pass = etPass.text.toString().trim()

        if (email.isBlank() || pass.isBlank()) {
            Toast.makeText(this, "Email/Password required", Toast.LENGTH_SHORT).show()
            return
        }

        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Log In failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
}