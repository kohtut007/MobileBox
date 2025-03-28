package com.homeworks.mobilebox.ui

import android.R
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.homeworks.mobilebox.databinding.ActivityCheckOutBinding

class CheckOutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckOutBinding
    private var retrieveTypeItems = listOf("Pick up", "Delivery")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckOutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        catchEvent()

        val arrayAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, retrieveTypeItems)
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinnerRetrieveType.adapter = arrayAdapter

        binding.spinnerRetrieveType.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectedItem = parent.getItemAtPosition(position).toString()
                    Toast.makeText(
                        this@CheckOutActivity,
                        "selected $selectedItem type.",
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }
    }

    private fun catchEvent() {
        binding.btnCheckOut.setOnClickListener {
            var address = binding.edtAddress.text.toString()
            var branch = binding.edtChooseBranch.text.toString()
            var paymentType = binding.edtPaymentType.text.toString()
            var phoneNumber = binding.edtPhoneNumber.text.toString()

            if (TextUtils.isEmpty(address)) {
                binding.edtAddress.error = "Address is required!"
                binding.edtAddress.requestFocus()
            } else if (TextUtils.isEmpty(branch)) {
                binding.edtChooseBranch.error = "Password is required!"
                binding.edtChooseBranch.requestFocus()
            } else if (TextUtils.isEmpty(paymentType)) {
                binding.edtPaymentType.error = "Confirm Password is required!"
                binding.edtPaymentType.requestFocus()
            } else if (TextUtils.isEmpty(phoneNumber)) {
                binding.edtPhoneNumber.error = "Phone number is required!"
                binding.edtPhoneNumber.requestFocus()
            } else {
                Toast.makeText(this, "Checkout", Toast.LENGTH_LONG).show()
            }
        }
    }
}