package com.example.kevin.a01higherlower.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kevin.a01higherlower.R

class ThrowListAdapter() : RecyclerView.Adapter<ThrowListAdapter.ViewHolder>() {

	private lateinit var throwList: List<Int>
	private lateinit var context: Context

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		this.context = parent.context
		val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
		return ViewHolder(v);
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.txtThrow.text = context.resources.getString(R.string.throw_item, throwList[position])
		if (position == 0 && itemCount > 1) holder.divider.visibility = View.INVISIBLE
		else holder.divider.visibility = View.VISIBLE
	}

	override fun getItemCount(): Int {
		return if (::throwList.isInitialized) throwList.size else 0
	}

	fun updateThrowList(throwList: List<Int>) {
		this.throwList = throwList
		notifyDataSetChanged()
	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		val txtThrow = itemView.findViewById<TextView>(R.id.tv_throw)!!
		val divider = itemView.findViewById<View>(R.id.divider)!!
	}

}