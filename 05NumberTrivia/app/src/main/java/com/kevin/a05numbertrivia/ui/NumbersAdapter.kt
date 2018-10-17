package com.kevin.a05numbertrivia.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kevin.a05numbertrivia.R
import com.kevin.a05numbertrivia.model.Number

class NumbersAdapter : RecyclerView.Adapter<NumbersAdapter.ViewHolder>() {

    private var numbers: List<Number>? = null
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        this.context = parent.context
        val v = when (viewType) {
            0 -> LayoutInflater.from(context).inflate(R.layout.item_number, parent, false)
            1 -> LayoutInflater.from(context).inflate(R.layout.item_number_reverse, parent, false)
            else -> LayoutInflater.from(context).inflate(R.layout.item_number, parent, false)
        }

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return numbers?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            tvNumber.text = numbers!![position].number.toString()
            tvTrivia.text = numbers!![position].text
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            position % 2 == 0 -> 0
            else -> 1
        }
    }

    fun updateNumbers(numbers: List<Number>?) {
        this.numbers = numbers
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNumber = itemView.findViewById<TextView>(R.id.tv_number)!!
        val tvTrivia = itemView.findViewById<TextView>(R.id.tv_trivia)!!
    }
}