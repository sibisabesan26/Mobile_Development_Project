package com.example.mobile_project

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.mobile_project.data.AppDatabaseHelper
import com.example.mobile_project.models.Task

class TaskAdapter(
    private val context: Context,
    private val tasks: MutableList<Task>,
    private val dbHelper: AppDatabaseHelper
) : BaseAdapter() {

    override fun getCount() = tasks.size
    override fun getItem(position: Int) = tasks[position]
    override fun getItemId(position: Int) = tasks[position].id.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_task, parent, false)
        val task = tasks[position]

        val title = view.findViewById<TextView>(R.id.tvTaskTitle)
        val checkbox = view.findViewById<CheckBox>(R.id.cbCompleted)
        val deleteBtn = view.findViewById<Button>(R.id.btnDelete)

        title.text = task.title
        checkbox.isChecked = task.isCompleted

        checkbox.setOnCheckedChangeListener { _, isChecked ->
            dbHelper.updateTaskStatus(task.id, isChecked)
            task.isCompleted = isChecked
        }

        deleteBtn.setOnClickListener {
            dbHelper.deleteTask(task.id)
            tasks.removeAt(position)
            notifyDataSetChanged()
        }

        return view
    }
}
