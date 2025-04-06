package com.homeworks.mobilebox.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout
import com.homeworks.mobilebox.databinding.ActivityLoginBinding
import com.homeworks.mobilebox.presentation.ui.register.RegisterActivity
import com.homeworks.mobilebox.ui.home.HomeActivity
import com.homeworks.mobilebox.ui.util.viewBinding

class LoginActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityLoginBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        tvRegister.setOnClickListener {
            navigateToRegister()
        }

        btLogin.setOnClickListener {
            validateAndLogin()
        }

        clearErrorsOnInput(etEmail, tiEmail)
        clearErrorsOnInput(etPassword, tiPassword)
    }

    private fun clearErrorsOnInput(editText: EditText, textInputLayout: TextInputLayout) {
        editText.addTextChangedListener {
            if (!it.isNullOrEmpty()) {
                textInputLayout.error = null
            }
        }
    }

    private fun validateAndLogin() = with(binding) {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        when {
            email.isEmpty() -> {
                tiEmail.error = "Email is required!"
                etEmail.requestFocus()
            }

            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                tiEmail.error = "Please enter a valid email address!"
                etEmail.requestFocus()
            }

            password.isEmpty() -> {
                tiPassword.error = "Password is required!"
                etPassword.requestFocus()
            }

            else -> {
                tiEmail.error = null
                tiPassword.error = null
                navigateToHome()
            }
        }
    }

    private fun navigateToRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    private fun navigateToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}

