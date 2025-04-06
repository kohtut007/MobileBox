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
    private var paymentTypeItems = listOf("KBZ Pay", "Wave Pay")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckOutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        catchEvent()

        val retrieveArrayAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, retrieveTypeItems)
        retrieveArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinnerRetrieveType.adapter = retrieveArrayAdapter

        binding.spinnerRetrieveType.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectedItem = parent.getItemAtPosition(position).toString()
//                    Toast.makeText(
//                        this@CheckOutActivity,
//                        "selected $selectedItem type.",
//                        Toast.LENGTH_LONG
//                    ).show()
                    when(selectedItem) {
                        "Pick up" -> {
                            binding.edtAddress.visibility = View.GONE
                            binding.edtChooseBranch.visibility = View.VISIBLE
//                            binding.edtPhoneNumber.text = // user register phone
                        }
                        "Delivery" -> {
                            binding.edtAddress.visibility = View.VISIBLE
                            binding.edtChooseBranch.visibility = View.GONE
                        }
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }

        var paymentArrayAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, paymentTypeItems)
        paymentArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinnerPaymentType.adapter = paymentArrayAdapter
    }

    private fun catchEvent() {
        binding.btnCheckOut.setOnClickListener {
            var address = binding.edtAddress.text.toString()
            var branch = binding.edtChooseBranch.text.toString()
            var phoneNumber = binding.etPhoneNumber.text.toString()

            if (TextUtils.isEmpty(address)) {
                binding.edtAddress.error = "Address is required!"
                binding.edtAddress.requestFocus()
            } else if (TextUtils.isEmpty(branch)) {
                binding.edtChooseBranch.error = "Password is required!"
                binding.edtChooseBranch.requestFocus()
            } else if (TextUtils.isEmpty(phoneNumber)) {
                binding.etPhoneNumber.error = "Phone number is required!"
                binding.etPhoneNumber.requestFocus()
            } else {
                Toast.makeText(this, "Checkout", Toast.LENGTH_LONG).show()
            }
        }
    }
}