package com.example.callapp.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.callapp.data.model.contact

class contactAdapter (private val contactList: List<contact>) : RecyclerView.Adapter<contactAdapter.contactViewholder>() {
    private val tag = "contactAdapter"

    class contactViewholder(itemView: View): RecyclerView.ViewHolder (itemView){
        fun bind (contact: contact) {
            itemView.findViewById<TextureView>(R.id.tvcontact).text= contact.firstName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): contactViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return contactViewholder(view)
    }

    override fun getItemCount(): Int {
        Log.d(tag, "getItemCount: ${contactList.size}")
        return contactList.size
    }

    override fun onBindViewHolder(holder: contactViewholder, position: Int) {
        val user = contactList[position]
        holder.bind(user)

        holder.itemView.setOnClickListener {
            Log.d(tag, "onBindViewHolder: ${user.number}")
        }
    }
}