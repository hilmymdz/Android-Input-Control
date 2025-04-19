package com.example.inputcontrol

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonDate: Button = findViewById(R.id.buttonDate)
        val tvSelection: TextView = findViewById(R.id.textViewSelection1)
        buttonDate.setOnClickListener {
            val now = Calendar.getInstance()
            DatePickerDialog(
                this,
                { _, year, month, day ->
                    tvSelection.text = "Selected date: %02d/%02d/%d".format(day, month + 1, year)
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
            ).show() // Uses Android's DatePickerDialog class :contentReference[oaicite:3]{index=3}
        }

        val buttonTime: Button = findViewById(R.id.buttonTime)
        val textViewSelection: TextView = findViewById(R.id.textViewSelection2)
        buttonTime.setOnClickListener {
            val now = Calendar.getInstance()
            TimePickerDialog(
                this,
                { _, hourOfDay, minute ->
                    textViewSelection.text = "Selected time: %02d:%02d".format(hourOfDay, minute)
                },
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                true
            ).show() // Uses Android's TimePickerDialog class :contentReference[oaicite:4]{index=4}
        }

        val btnShowPhone: Button = findViewById(R.id.btnShowPhone)
        val editTextPhone: EditText = findViewById(R.id.editTextPhone)
        btnShowPhone.setOnClickListener {
            val phoneNumber = editTextPhone.text.toString().trim()
            if (phoneNumber.isNotEmpty()) {
                Toast.makeText(this, "Phone: $phoneNumber", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter a phone number first", Toast.LENGTH_SHORT).show()
            }
        }
    }
}