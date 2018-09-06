package com.kevin.a02geoguessswipe.util

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.kevin.a02geoguessswipe.ui.StreetViewListAdapter

class StreetViewListSwipeCallback(private val recyclerView: RecyclerView,
								  private val streetViewListAdapter: StreetViewListAdapter) :
		ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

	override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
		return false
	}

	override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
		val streetViewItem = streetViewListAdapter.getItem(viewHolder.adapterPosition)!!

		if (direction == ItemTouchHelper.LEFT) {
			when (streetViewItem.inEurope) {
				true -> Snackbar.make(recyclerView, "Correct: ${streetViewItem.country} is in Europe", Snackbar.LENGTH_LONG).show()
				false -> Snackbar.make(recyclerView, "Wrong: ${streetViewItem.country} is not in Europe", Snackbar.LENGTH_LONG).show()
			}
		}

		if (direction == ItemTouchHelper.RIGHT) {
			when (streetViewItem.inEurope) {
				true -> Snackbar.make(recyclerView, "Wrong: ${streetViewItem.country} is in Europe", Snackbar.LENGTH_LONG).show()
				false -> Snackbar.make(recyclerView, "Correct: ${streetViewItem.country} is not in Europe", Snackbar.LENGTH_LONG).show()
			}
		}

		streetViewListAdapter.notifyItemChanged(viewHolder.adapterPosition)
	}

	override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
		val itemView = viewHolder.itemView
		val background: ColorDrawable

		val adapter = recyclerView.adapter as StreetViewListAdapter
		val streetView = adapter.getItem(viewHolder.adapterPosition)!!

		if (dX > 0) { // Swiping right
			background = ColorDrawable(Color.RED)
			background.setBounds(0, itemView.top, itemView.left + dX.toInt(), itemView.bottom)
			background.draw(c)
		} else { // Swiping left
			background = ColorDrawable(Color.GREEN)
			background.setBounds(0, itemView.top, itemView.right - dX.toInt(), itemView.bottom)
			background.draw(c)
		}

		super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
	}
}