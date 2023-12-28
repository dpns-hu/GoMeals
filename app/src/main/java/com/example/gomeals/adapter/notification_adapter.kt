package com.example.gomeals.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gomeals.databinding.NotificationSheetBinding

class notification_adapter ( var notificationText:ArrayList<String>, var notificationImage:ArrayList<Int>) :
    RecyclerView.Adapter<notification_adapter.notificationViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notificationViewHolder {
        val binding = NotificationSheetBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return notificationViewHolder(binding)
    }

    override fun getItemCount(): Int {
      return notificationText.size
    }

    override fun onBindViewHolder(holder: notificationViewHolder, position: Int) {
        holder.bind(position)
    }
   inner class notificationViewHolder(private var binding:NotificationSheetBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
          binding.apply{
              notificationImageView.setImageResource(notificationImage[position])
              notificationTextView.text = notificationText[position]
          }
        }

    }
}