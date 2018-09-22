package com.kevin.a03studentportal.ui

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kevin.a03studentportal.R
import com.kevin.a03studentportal.model.Portal

class StudentPortalsAdapter(private val callback: (Portal) -> Unit) : RecyclerView.Adapter<StudentPortalsAdapter.ViewHolder>() {

	private lateinit var portals: List<Portal>
	private lateinit var context: Context

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		this.context = parent.context
		val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item_portal, parent, false)
		return ViewHolder(v);
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.tvPortalTitle.text = portals[position].title
		holder.rootLayout.setOnClickListener { callback(portals[position]) }
	}

	override fun getItemCount(): Int {
		return if (::portals.isInitialized) portals.size else 0
	}

	fun getItem(position: Int): Portal? {
		return if (::portals.isInitialized) portals[position] else null
	}

	fun updateStudentPortals(portals : List<Portal>) {
		this.portals = portals
		notifyDataSetChanged()
	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		val tvPortalTitle = itemView.findViewById<TextView>(R.id.tv_portal_title)!!
		val rootLayout = itemView.findViewById<ConstraintLayout>(R.id.list_item_portal_root)!!

	}

}