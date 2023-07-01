package com.example.todoapp.ui

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todoapp.repository.Importance
import com.example.todoapp.R
import com.example.todoapp.repository.TodoItem
import com.example.todoapp.repository.TodoItemsRepository
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import java.time.LocalDateTime
import java.time.Month
import java.util.*

class AddItemFragment : Fragment() {
    val newid = (TodoItemsRepository.todoitems.size.plus(1)).toString()
    var todo = TodoItem(newid, "")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_item, container, false)
        val menuImp = view.findViewById<TextView>(R.id.tvOpenMenu)
        menuImp.setOnClickListener{
            showMenu(it, R.menu.menu_importance)
        }

        val switch = view.findViewById<SwitchMaterial>(R.id.swDeadline)
        val deadline = view.findViewById<TextView>(R.id.tvDeadline)
        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                showCalendar(deadline)
            }
            else {
                deadline.text = ""
                todo.deadline = null
            }
        }

        val appbar = view.findViewById<MaterialToolbar>(R.id.tabButtons)
        val delete = view.findViewById<Button>(R.id.btnDelete)
        appbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_addItemFragment_to_todosFragment)
        }
        delete.setOnClickListener {
            findNavController().navigate(R.id.action_addItemFragment_to_todosFragment)
        }



        return view
    }

    fun showMenu(v: View, @MenuRes menuRes: Int) {
        val popup = PopupMenu(requireContext(), v)
        popup.menuInflater.inflate(menuRes, popup.menu)
        popup.setOnMenuItemClickListener { menuItem: MenuItem ->
            // Respond to menu item click.
            when(menuItem.title) {
                "Нет" -> {
                    (v as TextView).text = menuItem.title.toString()
                    todo.importance = Importance.NORMAL
                }
                "Низкий" -> {
                    (v as TextView).text = menuItem.title.toString()
                    todo.importance = Importance.LOW
                }
                "!!Высокий" -> {
                    (v as TextView).text = menuItem.title.toString()
                    todo.importance = Importance.URGENT
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
                    todo.deadline = LocalDateTime.of(years, Month.of(months+1), days, 0, 0 ,0)
                }
            }, year, month, day)
        datePickerDialog.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btnSave -> {
                todo.text = view?.findViewById<TextInputEditText>(R.id.etTodo)?.text.toString()
                if(!todo.text.isEmpty()) {
                    TodoItemsRepository.todoitems.add(todo)
                    findNavController().navigate(R.id.action_addItemFragment_to_todosFragment)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }




}