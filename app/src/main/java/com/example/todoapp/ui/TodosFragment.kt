package com.example.todoapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.App
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentTodosBinding
import com.example.todoapp.repository.TodoItem
import com.example.todoapp.repository.TodoItemsRepository
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class TodosFragment: Fragment() {
    lateinit var binding: FragmentTodosBinding
    private val viewmodel : TodosViewModel by viewModels {ViewModelFactory((requireContext() as App).repository)}
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTodosBinding.inflate(inflater, container, false)

        setupRecyclerView(binding.rvItems)

        binding.btnAddItemPage.setOnClickListener {
            findNavController().navigate(R.id.action_todosFragment_to_addItemFragment)
        }

        return binding.root
    }

    private fun setupRecyclerView(recView : RecyclerView) {
        val adapter = Adapter()
        recView.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewmodel.todoList.collectLatest {
                if(!binding.cbHideDone.isChecked){
                    adapter.submitList(it)
                }
                else {
                    adapter.submitList(it.filter { !it.isDone })
                }
            }
        }

    }



}