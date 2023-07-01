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
import com.example.todoapp.repository.TodoItem

class Adapter : ListAdapter<TodoItem, Adapter.ViewHolder>(TodoDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).
                inflate(R.layout.item_todo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = getItem(position)
        holder.isDone.isChecked = task.isDone
        holder.text.text = task.text

    }

    class ViewHolder (item: View) : RecyclerView.ViewHolder(item){
        val isDone = item.findViewById<CheckBox>(R.id.ibIsDone)
        val text = item.findViewById<TextView>(R.id.tvText)
    }

}

class TodoDiffUtil: DiffUtil.ItemCallback<TodoItem>(){


    override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
        return oldItem == newItem
    }
}
