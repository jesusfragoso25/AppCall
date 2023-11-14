package com.example.callapp.ui.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.callapp.data.model.contact
import com.example.callapp.ui.activities.MainActivity
import com.example.callapp.ui.activities.contactEdit
import com.example.callapp.ui.viewmodel.contactViewModel
import com.example.callapp.utils.common

class contactAdapter(private val contactList: List<contact>) : RecyclerView.Adapter<contactAdapter.contactViewHolder>() {
    private val tag = "contactAdapter"

    class contactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(contact: contact) {
            itemView.findViewById<TextView>(R.id.contactId).text = contact.idContact.toString()
            itemView.findViewById<TextView>(R.id.name).text = contact.firstName
            itemView.findViewById<TextView>(R.id.lastName).text = contact.lastName
            itemView.findViewById<TextView>(R.id.phone).text = contact.number
            itemView.findViewById<TextView>(R.id.email).text = contact.mail
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): contactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return contactViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d(tag, "getItemCount: ${contactList.size}")
        return contactList.size
    }

    override fun onBindViewHolder(holder: contactViewHolder, position: Int) {
        val contact = contactList[position]
        holder.bind(contact)

        holder.itemView.findViewById<Button>(R.id.eliminar).setOnClickListener {
            // Aquí obtienes el ID del contacto y realizas la acción necesaria
            val contactId = contact.idContact.toString().toLong()
            deleteContact(contactId, holder.itemView.context)
            showToast("Contacto eliminado", holder.itemView.context)

            val intent = Intent(holder.itemView.context, MainActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }

        holder.itemView.findViewById<Button>(R.id.editar).setOnClickListener {
            val intent = Intent(holder.itemView.context, contactEdit::class.java)
            intent.putExtra("contactId", holder.itemView.findViewById<TextView>(
                R.id.contactId).text.toString())
            intent.putExtra("name", holder.itemView.findViewById<TextView>(
                R.id.name).text.toString())
            intent.putExtra("email", holder.itemView.findViewById<TextView>(
                R.id.email).text.toString())
            intent.putExtra("phone", holder.itemView.findViewById<TextView>(
                R.id.phone).text.toString())
            intent.putExtra("lastName", holder.itemView.findViewById<TextView>(
                R.id.lastName).text.toString())
            holder.itemView.context.startActivity(intent)
        }

        holder.itemView.findViewById<Button>(R.id.llamar).setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + holder.itemView.findViewById<TextView>(
                R.id.phone).text.toString())
            holder.itemView.context.startActivity(intent)
        }

        holder.itemView.findViewById<Button>(R.id.sendEmail).setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:" + holder.itemView.findViewById<TextView>(
                R.id.email).text.toString())
            holder.itemView.context.startActivity(intent)
        }
    }

    private fun deleteContact(contactId: Long, context: Context) {
        val contactViewModel: contactViewModel =
            ViewModelProvider(context as ViewModelStoreOwner)[contactViewModel::class.java]
        contactViewModel.deleteContact(contactId)
    }

    private fun showToast(message: String, context: Context) {
        common.showToast(context, message)
    }

}