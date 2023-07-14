package com.example.todoapp.ui
import android.app.ActionBar
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toolbar
import androidx.annotation.MenuRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todoapp.App
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentAddItemBinding
import com.example.todoapp.repository.Importance
import com.google.android.material.switchmaterial.SwitchMaterial
import java.util.Calendar
import java.util.UUID

class AddItemFragment : Fragment() {
    lateinit var binding : FragmentAddItemBinding
    private val viewmodel : AddItemViewModel by viewModels {ViewModelFactory((requireContext().applicationContext as App).repository)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddItemBinding.inflate(inflater, container, false)

        var isNew = true
        binding.tvOpenMenu.setOnClickListener{
            showMenu(it, R.menu.menu_importance)
        }
        isDeadlineNeed(binding.swDeadline)
        binding.tabAddInc.tabButtons.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_addItemFragment_to_todosFragment)
        }
        binding.btnDelete.setOnClickListener {
            findNavController().navigate(R.id.action_addItemFragment_to_todosFragment)
        }

        saveTodoItem(isNew)

        return binding.root
    }


    private fun addTodoItem(isNew : Boolean) {
        with(viewmodel.currentItem) {
            text = binding.etTodo.text.toString()
            if(isNew){
                id = UUID.randomUUID().toString()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    creationDate = System.currentTimeMillis().toString()
                }
                viewmodel.addItem(this)
            }
            else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    modificationDate = System.currentTimeMillis().toString()
                }
                viewmodel.addItem(this)
            }
        }
    }
    private fun isDeadlineNeed(switch: SwitchMaterial) {
        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                showCalendar(binding.tvDeadline)
            }
            else {
                binding.tvDeadline.text = ""
                viewmodel.currentItem.deadline = null
            }
        }
    }
    fun showMenu(v: View, @MenuRes menuRes: Int)  {
        val popup = PopupMenu(requireContext(), v)
        popup.menuInflater.inflate(menuRes, popup.menu)
        popup.setOnMenuItemClickListener { menuItem: MenuItem ->
            // Respond to menu item click.
            when(menuItem.title) {
                "Нет" -> {
                    (v as TextView).text = menuItem.title.toString()
                    viewmodel.currentItem.importance = Importance.NORMAL
                }
                "Низкий" -> {
                    (v as TextView).text = menuItem.title.toString()
                    viewmodel.currentItem.importance = Importance.LOW
                }
                "!!Высокий" -> {
                    (v as TextView).text = menuItem.title.toString()
                    viewmodel.currentItem.importance = Importance.URGENT
                }
            }
            Log.d("Popup menu", menuItem.itemId.toString())
            Log.d("Popup menu", menuItem.title.toString())
            true
        }
        popup.setOnDismissListener {
            // Respond to popup being dismissed.
        }
        // Show the popup menu.
        popup.show()
    }
    private fun showCalendar(tv: TextView) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog (
            requireContext(), {view, years, months, days ->
                val dat = ((if(days < 10) "0"  else "") + days + "/" + (if(months + 1 < 10) "0"  else "") + (months + 1) + "/" + years)
                tv.setText(dat)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    viewmodel.currentItem.deadline = "$days/$months/$years"
                }
            }, year, month, day)
        datePickerDialog.show()
    }

    fun saveTodoItem(isNew : Boolean) {
        binding.tabAddInc.tabButtons.inflateMenu(R.menu.menu_add_page)
        binding.tabAddInc.tabButtons.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.btnSave -> {
                    if (!viewmodel.currentItem.text.isEmpty()) {
                        addTodoItem(isNew)
                        findNavController().navigate(R.id.action_addItemFragment_to_todosFragment)
                    }
                    true
                }
                else -> {Log.d("Save todo", "Fill editText")
                    true
                }
            }
        }
    }

}