package com.example.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Data model
data class Schedule(val day: String, val type: String)

// Adapter with click listener
class ScheduleAdapter(
    private val scheduleList: List<Schedule>,
    private val onItemClick: (Schedule) -> Unit
) : RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

    // ViewHolder
    class ScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dayText: TextView = itemView.findViewById(R.id.txtDay)
        val typeText: TextView = itemView.findViewById(R.id.txtType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.schedule_item, parent, false)
        return ScheduleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val schedule = scheduleList[position]
        holder.dayText.text = schedule.day
        holder.typeText.text = schedule.type

        // Handle click
        holder.itemView.setOnClickListener {
            onItemClick(schedule)
        }
    }

    override fun getItemCount(): Int = scheduleList.size
}
