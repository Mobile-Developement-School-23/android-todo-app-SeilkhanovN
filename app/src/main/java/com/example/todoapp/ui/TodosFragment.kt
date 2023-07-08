package com.example.todoapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentTodosBinding
import com.example.todoapp.repository.TodoItemsRepository

class TodosFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTodosBinding.inflate(inflater, container, false)

        setupRecyclerView(binding.rvItems)

        binding.btnAddItemPage.setOnClickListener {
            findNavController().navigate(R.id.action_todosFragment_to_addItemFragment)
        }

        return binding.root
    }

    private fun setupRecyclerView(recView : RecyclerView) {
        val adapter = Adapter()
        recView.adapter = adapter
        adapter.submitList(TodoItemsRepository.todoitems)
    }



}