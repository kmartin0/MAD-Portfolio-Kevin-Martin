package com.kevin.a04gamebacklog.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kevin.a04gamebacklog.R
import com.kevin.a04gamebacklog.model.Game

class GamesAdapter : RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

    private lateinit var games: List<Game>
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        this.context = parent.context
        val v = LayoutInflater.from(context).inflate(R.layout.item_game, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return if (::games.isInitialized) games.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = games[position].title
        holder.tvPlatform.text = games[position].platform
        holder.tvStatus.text = games[position].status
        holder.tvDate.text = games[position].date

//        holder.tvInfo.text = games[position].title

    }

    fun updateGames(games: List<Game>) {
        this.games = games
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)!!
        val tvPlatform = itemView.findViewById<TextView>(R.id.tv_platform)!!
        val tvStatus = itemView.findViewById<TextView>(R.id.tv_status)!!
        val tvDate = itemView.findViewById<TextView>(R.id.tv_date)!!
//        val tvInfo = itemView.findViewById<TextView>(R.id.info_text)!!
    }
}