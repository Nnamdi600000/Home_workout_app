package com.codennamdi.homeworkoutapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codennamdi.homeworkoutapp.databinding.ActivityBmiBinding

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

        binding.toolBarBmi.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}