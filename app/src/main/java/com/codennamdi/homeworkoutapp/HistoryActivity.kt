package com.codennamdi.homeworkoutapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.codennamdi.homeworkoutapp.databinding.ActivityHistoryBinding
import kotlinx.coroutines.launch

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

        val completedExerciseDao = (application as CompletedExerciseApp).db.completedExerciseDao()
        lifecycleScope.launch {
            completedExerciseDao.loadAllCompletedExercises().collect {
                val completedExerciseList = ArrayList(it)
                setUpAdapter(completedExerciseList)
            }
        }
    }

    private fun setUpAdapter(completedExerciseList: ArrayList<CompletedExerciseEntity>) {
        if (completedExerciseList.isNotEmpty()) {
            val listAdapter = ListOfCompletedExerciseAdapter(completedExerciseList)
            binding.recyclerViewListOfCompletedExercise.visibility = View.VISIBLE
            binding.recyclerViewListOfCompletedExercise.layoutManager = LinearLayoutManager(this@HistoryActivity)
            binding.textViewNoRecentActivity.visibility = View.GONE
            binding.recyclerViewListOfCompletedExercise.adapter = listAdapter
        } else {
            binding.recyclerViewListOfCompletedExercise.visibility = View.INVISIBLE
            binding.textViewNoRecentActivity.visibility = View.VISIBLE
        }
    }
}