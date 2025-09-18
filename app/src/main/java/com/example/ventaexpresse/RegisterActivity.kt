package com.example.ventaexpresse

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    private lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    private lateinit var etConfPass: EditText
    private lateinit var btnSignUp: Button
    private lateinit var tvRedirectLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etEmail = findViewById(R.id.etSEmailAddress)
        etPass = findViewById(R.id.etSPassword)
        etConfPass = findViewById(R.id.etSConfPassword)
        btnSignUp = findViewById(R.id.btnSSigned)
        tvRedirectLogin = findViewById(R.id.tvRedirectLogin)

        auth = Firebase.auth

        btnSignUp.setOnClickListener { signUpUser() }

        tvRedirectLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        // Si ya hay sesión, ir directo a Main
        auth.currentUser?.let {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun signUpUser() {
        val email = etEmail.text.toString().trim()
        val pass = etPass.text.toString().trim()
        val confirm = etConfPass.text.toString().trim()

        if (email.isBlank() || pass.isBlank() || confirm.isBlank()) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }
        if (pass != confirm) {
            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Successfully Registered", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Signed Up Failed!", Toast.LENGTH_SHORT).show()
                }
            }
    }
}