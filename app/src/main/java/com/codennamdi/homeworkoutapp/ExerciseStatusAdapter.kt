package com.codennamdi.homeworkoutapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.codennamdi.homeworkoutapp.databinding.ItemExerciseStatusBinding

class ExerciseStatusAdapter(val items: ArrayList<ExerciseModel>) :
    RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {

    //We created our own ViewHolder
    inner class ViewHolder(binding: ItemExerciseStatusBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val textViewItem = binding.textViewItem
    }

    //Returning a view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemExerciseStatusBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    //Setting what we want each element to do
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: ExerciseModel = items[position]
        holder.textViewItem.text =
            model.getId().toString() //We are setting the ids to each of the text view

        //We are setting what it should do if the exercise is selected, completed, or not reached.
        when {
            model.getIsComplete() -> {
                holder.textViewItem.background = ContextCompat.getDrawable(
                    holder.itemView.context,
                    R.drawable.item_circular_color_accent_background
                )
                holder.textViewItem.setTextColor(Color.parseColor("#FFFFFFFF"))
            }
            model.getIsSelected() -> {
                holder.textViewItem.background = ContextCompat.getDrawable(
                    holder.itemView.context,
                    R.drawable.item_circular_is_complete_background
                )
                holder.textViewItem.setTextColor(Color.parseColor("#FF000000"))
            }
            else -> {
                holder.textViewItem.background = ContextCompat.getDrawable(
                    holder.itemView.context,
                    R.drawable.item_circular_color_grey_background
                )
                holder.textViewItem.setTextColor(Color.parseColor("#212121"))
            }
        }
    }

    //We are checking how many item to be displayed
    override fun getItemCount(): Int {
        return items.size
    }
}