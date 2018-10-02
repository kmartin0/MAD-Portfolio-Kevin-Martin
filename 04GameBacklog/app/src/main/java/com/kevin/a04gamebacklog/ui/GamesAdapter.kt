package com.kevin.a04gamebacklog.ui

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kevin.a04gamebacklog.R
import com.kevin.a04gamebacklog.model.Game

class GamesAdapter(private val callback: (Game?) -> Unit) : RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

	private var games: List<Game>? = null
	private lateinit var context: Context

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		this.context = parent.context
		val v = LayoutInflater.from(context).inflate(R.layout.item_game, parent, false)
		return ViewHolder(v)
	}

	override fun getItemCount(): Int {
		return games?.size ?: 0
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.apply {
			tvTitle.text = games?.get(position)?.title
			tvPlatform.text = games?.get(position)?.platform
			tvStatus.text = games?.get(position)?.status
			tvDate.text = games?.get(position)?.date
			cvRoot.setOnClickListener { callback(games?.get(position)) }
		}
	}

	fun updateGames(games: List<Game>?) {
		this.games = games
		notifyDataSetChanged()
	}

	fun getItem(position: Int): Game? {
		return games?.get(position)
	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)!!
		val tvPlatform = itemView.findViewById<TextView>(R.id.tv_platform)!!
		val tvStatus = itemView.findViewById<TextView>(R.id.tv_status)!!
		val tvDate = itemView.findViewById<TextView>(R.id.tv_date)!!
		val cvRoot = itemView.findViewById<CardView>(R.id.card_view)!!
	}

}