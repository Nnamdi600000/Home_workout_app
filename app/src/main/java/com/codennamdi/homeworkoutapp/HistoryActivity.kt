package com.codennamdi.homeworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codennamdi.homeworkoutapp.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBarHistory)
        title = "History"
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding.toolBarHistory.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}