package com.homeworks.mobilebox.ui.auth

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
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

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        catchEvent()
    }

    private fun catchEvent() {
        binding.apply {
            btnRegister.setOnClickListener {
                val email = edtEmail.text.toString().trim()
                val name = edtUsername.text.toString().trim()
                val phoneNumber = edtPhoneNumber.text.toString().trim()
                val password = edtPassword.text.toString().trim()
                val confirmPassword = edtConfirmPassword.text.toString().trim()

                // Validate email is not empty
                if (TextUtils.isEmpty(email)) {
                    edtEmail.error = "Email is required!"
                    edtEmail.requestFocus()
                    return@setOnClickListener
                }
                // Validate email pattern
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    edtEmail.error = "Please enter a valid email address!"
                    edtEmail.requestFocus()
                    return@setOnClickListener
                }
                // Validate name
                if (TextUtils.isEmpty(name)) {
                    edtUsername.error = "Name is required!"
                    edtUsername.requestFocus()
                    return@setOnClickListener
                }
                // Validate phone number
                if (TextUtils.isEmpty(phoneNumber)) {
                    edtPhoneNumber.error = "Phone number is required!"
                    edtPhoneNumber.requestFocus()
                    return@setOnClickListener
                }
                if (!Patterns.PHONE.matcher(phoneNumber).matches()) {
                    edtPhoneNumber.error = "Please enter a valid phone number!"
                    edtPhoneNumber.requestFocus()
                    return@setOnClickListener
                }
                // Validate password
                if (TextUtils.isEmpty(password)) {
                    edtPassword.error = "Password is required!"
                    edtPassword.requestFocus()
                    return@setOnClickListener
                }
                // Validate confirm password
                if (TextUtils.isEmpty(confirmPassword)) {
                    edtConfirmPassword.error = "Confirm Password is required!"
                    edtConfirmPassword.requestFocus()
                    return@setOnClickListener
                }
                // Validate that password and confirm password match
                if (password != confirmPassword) {
                    edtConfirmPassword.error = "Passwords do not match!"
                    edtConfirmPassword.requestFocus()
                    return@setOnClickListener
                }

                // If validations pass, show your confirmation dialog
                showConfirmDialog()

            }

            rlGoToLogin.setOnClickListener {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            }
        }
    }

    private fun showConfirmDialog() {
        val dialog = Dialog(this@RegisterActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.comfirm_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val edtCode: EditText = dialog.findViewById(R.id.edt_code)
        val btnResend: TextView = dialog.findViewById(R.id.tv_resend_again)
        val btnCancel: Button = dialog.findViewById(R.id.btn_cancel)
        val btnConfirm: Button = dialog.findViewById(R.id.btn_confirm)
        val progressLoading: ProgressBar = dialog.findViewById(R.id.pb_loading)
        dialog.show()

        btnResend.setOnClickListener {
            progressLoading.visibility = View.VISIBLE
        }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        btnConfirm.setOnClickListener {
            if (edtCode.text.isNullOrEmpty()) {
                edtCode.error = "Please enter code"
            } else {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            }
        }
    }
}
