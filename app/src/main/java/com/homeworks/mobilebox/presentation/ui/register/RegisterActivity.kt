package com.homeworks.mobilebox.presentation.ui.register

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.homeworks.mobilebox.R
import com.homeworks.mobilebox.databinding.ActivityRegisterBinding
import com.homeworks.mobilebox.presentation.ui.login.LoginActivity
import com.homeworks.mobilebox.ui.util.viewBinding

class RegisterActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityRegisterBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        btRegister.setOnClickListener {
            if (validateForm()) {
                showConfirmDialog()
            }
        }

        tvLogin.setOnClickListener {
            navigateToLogin()
        }
    }

    private fun validateForm(): Boolean = with(binding) {
        val email = etEmail.text.toString().trim()
        val name = etName.text.toString().trim()
        val phoneNumber = etPhoneNumber.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val confirmPassword = etCPassword.text.toString().trim()

        return when {
            email.isEmpty() -> {
                etEmail.error = "Email is required!"
                etEmail.requestFocus()
                false
            }

            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                etEmail.error = "Please enter a valid email address!"
                etEmail.requestFocus()
                false
            }

            name.isEmpty() -> {
                etName.error = "Name is required!"
                etName.requestFocus()
                false
            }

            phoneNumber.isEmpty() -> {
                etPhoneNumber.error = "Phone number is required!"
                etPhoneNumber.requestFocus()
                false
            }

            !Patterns.PHONE.matcher(phoneNumber).matches() -> {
                etPhoneNumber.error = "Please enter a valid phone number!"
                etPhoneNumber.requestFocus()
                false
            }

            password.isEmpty() -> {
                etPassword.error = "Password is required!"
                etPassword.requestFocus()
                false
            }

            confirmPassword.isEmpty() -> {
                etCPassword.error = "Confirm Password is required!"
                etCPassword.requestFocus()
                false
            }

            password != confirmPassword -> {
                etCPassword.error = "Passwords do not match!"
                etCPassword.requestFocus()
                false
            }

            else -> true
        }
    }

    private fun showConfirmDialog() {
        val dialog = Dialog(this).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(R.layout.comfirm_dialog)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        val edtCode: EditText = dialog.findViewById(R.id.edt_code)
        val btnResend: TextView = dialog.findViewById(R.id.tv_resend_again)
        val btnCancel: Button = dialog.findViewById(R.id.btn_cancel)
        val btnConfirm: Button = dialog.findViewById(R.id.btn_confirm)
        val progressLoading: ProgressBar = dialog.findViewById(R.id.pb_loading)

        btnResend.setOnClickListener {
            progressLoading.visibility = View.VISIBLE
            // Simulate resend logic
        }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        btnConfirm.setOnClickListener {
            if (edtCode.text.isNullOrEmpty()) {
                edtCode.error = "Please enter code"
            } else {
                navigateToLogin()
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}

