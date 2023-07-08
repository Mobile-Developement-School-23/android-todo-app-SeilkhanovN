package com.example.todoapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.databinding.ItemTodoBinding
import com.example.todoapp.repository.Importance
import com.example.todoapp.repository.TodoItem

class Adapter : ListAdapter<TodoItem, Adapter.ViewHolder>(TodoDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ViewHolder(
            ItemTodoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
        return view
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task)
    }

    class ViewHolder(private val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: TodoItem) {
            with(binding) {
                tvText.text = task.text

                ibIsDone.isChecked = task.isDone
                ibIsDone.setOnClickListener {
                    task.isDone = ibIsDone.isChecked
                    tvText.paint.isStrikeThruText = ibIsDone.isChecked
                    tvText.invalidate()
                }

                if (task.deadline != null) {
                    tvDeadline.text = task.deadline.toString()
                    tvDeadline.visibility = View.VISIBLE
                } else {
                    tvDeadline.visibility = View.GONE
                }

                when (task.importance) {
                    Importance.LOW -> ivImportance.setImageResource(R.drawable.ic_arrow_down)
                    Importance.NORMAL -> ivImportance.visibility = View.GONE
                    Importance.URGENT -> {
                        ivImportance.setImageResource(R.drawable.ic_warning)
                        ibIsDone.isErrorShown = true
                    }
                }
            }
        }
    }
}

class TodoDiffUtil : DiffUtil.ItemCallback<TodoItem>() {


    override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
        return oldItem == newItem
    }
}
