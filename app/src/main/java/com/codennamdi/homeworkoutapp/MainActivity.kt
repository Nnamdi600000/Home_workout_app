package com.codennamdi.homeworkoutapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codennamdi.homeworkoutapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpClickListeners()
    }

    private fun setUpClickListeners() {
        binding.frameLayoutStartBtn.setOnClickListener {
            val startButtonIntent = Intent(this, ExerciseActivity::class.java)
            startActivity(startButtonIntent)
        }

        binding.frameLayoutBmi.setOnClickListener {
            val intent = Intent(this@MainActivity, BmiActivity::class.java)
            startActivity(intent)
        }

        binding.frameLayoutHistory.setOnClickListener {
            val intent = Intent(this@MainActivity, HistoryActivity::class.java)
            startActivity(intent)
        }
    }
}