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
        title = "Bmi Calculator" //Making the toolbar title dynamic
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        setUpClickListener()
    }

    private fun setUpClickListener() {
        binding.toolBarBmi.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.calculateBtn.setOnClickListener {
            if (metricUnitValid()) {
                val weightValue: Float = binding.textFieldWeight.text.toString().toFloat()
                val heightValue: Float = binding.textFieldHeight.text.toString().toFloat() / 100
                val bmiCalculation = weightValue / (heightValue * heightValue) //Bmi formula.
                displayBmiResult(bmiCalculation)
            } else if (usUnitValid()) {
                val weightInPounds: Float = binding.textFieldWeight2.text.toString().toFloat()
                val feet: Float = binding.textFieldFeet.text.toString().toFloat()
                val inch: Float = binding.textFieldInch.text.toString().toFloat()

                //Bmi formula for us unit.
                val usHeightValue = inch + feet * 12
                val bmiUsUnit = 703 * (weightInPounds / (usHeightValue * usHeightValue))
                displayBmiResult(bmiUsUnit)
            } else {
                Toast.makeText(this@BmiActivity, "Please enter a value", Toast.LENGTH_SHORT).show()
            }
        }

        binding.radioButtonMetricUnit.setOnCheckedChangeListener { buttonView, _ ->
            if (buttonView.isChecked) {
                makeMetricUnitVisible()
            }
        }

        binding.radioButtonUSUnit.setOnCheckedChangeListener { buttonView, _ ->
            if (buttonView.isChecked) {
                makeUsUnitVisible()
            }
        }
    }

    private fun makeMetricUnitVisible() {
        binding.linearLayoutTextField.visibility = View.INVISIBLE
        binding.textFieldHeightInputLayout.visibility = View.VISIBLE
        binding.textFieldWeightInputLayout.visibility = View.VISIBLE
        binding.textFieldWeight2InputLayout.visibility = View.GONE

        binding.textFieldFeet.text!!.clear()
        binding.textFieldInch.text!!.clear()
        binding.textFieldWeight2.text!!.clear()

        binding.linearLayout.visibility = View.INVISIBLE
    }

    private fun makeUsUnitVisible() {
        binding.linearLayoutTextField.visibility = View.VISIBLE
        binding.textFieldWeight2InputLayout.visibility = View.VISIBLE
        binding.textFieldHeightInputLayout.visibility = View.GONE
        binding.textFieldWeightInputLayout.visibility = View.GONE

        binding.textFieldWeight.text!!.clear()
        binding.textFieldHeight.text!!.clear()

        binding.linearLayout.visibility = View.INVISIBLE
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

    private fun metricUnitValid(): Boolean {
        var isValid = true
        if (binding.textFieldWeight.text.toString().isEmpty()) {
            isValid = false
        } else if (binding.textFieldHeight.text.toString().isEmpty()) {
            isValid = false
        }
        return isValid
    }

    private fun usUnitValid(): Boolean {
        var isValid = true
        when {
            (binding.textFieldWeight2.text.toString().isEmpty()) -> {
                isValid = false
            }
            (binding.textFieldFeet.text.toString().isEmpty()) -> {
                isValid = false
            }
            (binding.textFieldInch.text.toString().isEmpty()) -> {
                isValid = false
            }
        }
        return isValid
    }
}