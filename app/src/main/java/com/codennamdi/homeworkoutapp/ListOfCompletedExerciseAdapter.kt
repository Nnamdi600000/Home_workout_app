package com.codennamdi.homeworkoutapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.codennamdi.homeworkoutapp.databinding.ItemCompletedExerciseHistoryBinding

class ListOfCompletedExerciseAdapter(private var items: ArrayList<CompletedExerciseEntity>) :
    RecyclerView.Adapter<ListOfCompletedExerciseAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemCompletedExerciseHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val linearLayoutItem = binding.linearLayoutHistoryItem
        val idNumber = binding.textViewId
        val timeAndDateTv = binding.textViewTimeAndDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCompletedExerciseHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context
        val item = items[position]


        holder.timeAndDateTv.text = item.timeAndDate
        holder.idNumber.text = item.id.toString()

        if (position % 2 == 0) {
            holder.linearLayoutItem.setBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.lightGrey
                )
            )
        } else {
            holder.linearLayoutItem.setBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.white
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}