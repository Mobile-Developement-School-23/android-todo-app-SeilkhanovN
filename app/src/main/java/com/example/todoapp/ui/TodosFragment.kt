package com.example.todoapp.ui
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.App
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentTodosBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TodosFragment: Fragment() {
    lateinit var binding: FragmentTodosBinding
    private val viewmodel : TodosViewModel by viewModels {ViewModelFactory((requireContext().applicationContext as App).repository)}
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