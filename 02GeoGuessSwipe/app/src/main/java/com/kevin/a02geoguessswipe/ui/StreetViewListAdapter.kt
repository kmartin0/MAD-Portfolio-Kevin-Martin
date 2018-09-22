package com.kevin.a02geoguessswipe.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.kevin.a02geoguessswipe.R
import com.kevin.a02geoguessswipe.model.StreetView
import android.view.animation.AnimationUtils.loadAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.bumptech.glide.request.RequestOptions


class StreetViewListAdapter() : RecyclerView.Adapter<StreetViewListAdapter.ViewHolder>() {

	private lateinit var streetViews: List<StreetView>
	private lateinit var context: Context

	private var lastPosition = -1

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		this.context = parent.context
		val v = LayoutInflater.from(parent.context).inflate(R.layout.streetview_list_item, parent, false)
		return ViewHolder(v);
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val options = RequestOptions()
		options.centerCrop()
		Glide.with(context).load(streetViews[position].imageRedId).apply(options).into(holder.ivStreetView)
	}

	override fun getItemCount(): Int {
		return if (::streetViews.isInitialized) streetViews.size else 0
	}

	fun getItem(position: Int): StreetView? {
		return if (::streetViews.isInitialized) streetViews[position] else null
	}

	fun updatestreetViewDrawableResIds(streetViewDrawableResIds: List<StreetView>) {
		this.streetViews = streetViewDrawableResIds
		notifyDataSetChanged()
	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		val ivStreetView = itemView.findViewById<ImageView>(R.id.iv_street_view)!!
	}

}