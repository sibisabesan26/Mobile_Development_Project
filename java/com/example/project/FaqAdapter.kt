package com.example.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView

class FaqAdapter(private val faqList: List<FaqItem>) :
    RecyclerView.Adapter<FaqAdapter.FaqViewHolder>() {

    inner class FaqViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtQuestion: TextView = itemView.findViewById(R.id.txtQuestion)
        val videoFaq: VideoView = itemView.findViewById(R.id.videoFaq)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.faq_item, parent, false)
        return FaqViewHolder(view)
    }

    override fun onBindViewHolder(holder: FaqViewHolder, position: Int) {
        val faq = faqList[position]
        holder.txtQuestion.text = faq.question

        // Toggle expand/collapse when question is tapped
        holder.txtQuestion.setOnClickListener {
            faq.isExpanded = !faq.isExpanded
            notifyItemChanged(position)
        }

        if (faq.isExpanded) {
            holder.videoFaq.visibility = View.VISIBLE
            holder.videoFaq.setVideoPath(faq.videoUrl)
            holder.videoFaq.start()
        } else {
            holder.videoFaq.visibility = View.GONE
            holder.videoFaq.stopPlayback()
        }
    }

    override fun getItemCount(): Int = faqList.size
}
