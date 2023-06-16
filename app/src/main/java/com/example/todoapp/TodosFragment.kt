package com.example.todoapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView

class TodosFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = layoutInflater.inflate(R.layout.fragment_todos, container, false)
        val recView = view.findViewById<RecyclerView>(R.id.rvItems)
        val adapter = Adapter()
        recView.adapter = adapter
        TodoItemsRepository.todoitems.forEach {
            Log.d("NurdauletTodo", it.text)
        }
        adapter.submitList(TodoItemsRepository.todoitems)


        view.findViewById<Button>(R.id.btnAddItemPage).setOnClickListener {
            findNavController().navigate(R.id.action_todosFragment_to_addItemFragment)
        }



        return view
    }


}