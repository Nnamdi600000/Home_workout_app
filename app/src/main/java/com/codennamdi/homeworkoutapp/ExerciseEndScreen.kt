package com.codennamdi.homeworkoutapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codennamdi.homeworkoutapp.databinding.ActivityExerciseEndScreenBinding

class ExerciseEndScreen : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseEndScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseEndScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBarEndScreen)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding.toolBarEndScreen.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.finishBtn.setOnClickListener {
            val intent = Intent(this@ExerciseEndScreen, MainActivity::class.java)
            startActivity(intent)
        }
    }
}