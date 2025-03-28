package com.homeworks.mobilebox.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.homeworks.mobilebox.databinding.ActivityLoginBinding
import com.homeworks.mobilebox.ui.home.HomeActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()

        binding.rlGoToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            validateAndLogin()
        }
    }

    private fun initListeners() {
        // Clear error when user types in email field
        binding.edtEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    // Clear error once user starts typing
                    binding.tilEmail.error = null
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // Clear error when user types in password field
        binding.edtPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    // Clear error once user starts typing
                    binding.tilPassword.error = null
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun validateAndLogin() {
        val email = binding.edtEmail.text.toString().trim()
        val password = binding.edtPassword.text.toString().trim()

        // Check if email is empty
        if (TextUtils.isEmpty(email)) {
            binding.tilEmail.error = "Email is required!"
            binding.edtEmail.requestFocus()
            return
        }

        // Check if email is valid
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = "Please enter a valid email address!"
            binding.edtEmail.requestFocus()
            return
        }

        // 3) Check if password is empty
        if (TextUtils.isEmpty(password)) {
            binding.tilPassword.error = "Password is required!"
            binding.edtPassword.requestFocus()
            return
        }

        // 4) Clear any leftover errors
        binding.tilEmail.error = null
        binding.tilPassword.error = null

        // 5) If everything is valid, go to Home
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}
