package com.codennamdi.homeworkoutapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codennamdi.homeworkoutapp.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BmiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBmiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBarBmi)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            title = "Bmi Calculator" //Making the toolbar title dynamic
        }
        setUpClickListener()
    }

    private fun setUpClickListener() {
        binding.toolBarBmi.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.calculateBtn.setOnClickListener {
            if (metricUnitValue()) {
                val weightValue: Float = binding.textFieldWeight.text.toString().toFloat()
                val heightValue: Float = binding.textFieldHeight.text.toString().toFloat() / 100
                val bmiCalculation = weightValue / (heightValue * heightValue) //Bmi formula.
                displayBmiResult(bmiCalculation)
            } else {
                Toast.makeText(this@BmiActivity, "Please enter a value", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun displayBmiResult(bmi: Float) {
        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very under weight"
            bmiDescription = "You really need to eat more."
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops! You really need to eat more."
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to eat more."
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape! Keep it up!"
        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0) {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }

        //Formatting the bmi value.
        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        binding.calculatedResult.text = bmiValue
        binding.textViewBmiComment.text = bmiLabel
        binding.textViewBmiDescription.text = bmiDescription

        binding.linearLayout.visibility = View.VISIBLE
    }

    private fun metricUnitValue(): Boolean {
        var isValid = true
        if (binding.textFieldWeight.text.toString().isEmpty()) {
            isValid = false
        } else if (binding.textFieldHeight.text.toString().isEmpty()) {
            isValid = false
        }
        return isValid
    }
}