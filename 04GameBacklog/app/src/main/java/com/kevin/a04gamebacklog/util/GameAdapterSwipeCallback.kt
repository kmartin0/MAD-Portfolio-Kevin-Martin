package com.kevin.a04gamebacklog.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import com.kevin.a04gamebacklog.R
import com.kevin.a04gamebacklog.database.GameRepository
import com.kevin.a04gamebacklog.ui.GamesAdapter

class GameAdapterSwipeCallback(private val view: View,
							   private val gamesAdapter: GamesAdapter,
							   private val gameRepository: GameRepository,
							   context: Context) :
		ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

	private val backgroundColor = ColorDrawable(Color.RED)
	private val recycleBinDrawable = ContextCompat.getDrawable(context, R.drawable.ic_delete_forever_black_24dp)?.apply {
		setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
	}
	private val recycleBinDrawableMargin = context.resources.getDimension(R.dimen.ic_clear_margin).toInt()

	override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
		return false
	}

	override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
		if (direction == ItemTouchHelper.LEFT) {
			val game = gamesAdapter.getItem(viewHolder.adapterPosition)!!
			gameRepository.delete(game)
			Snackbar.make(view, "Removed ${game.title}", Snackbar.LENGTH_LONG).apply {
				setAction("Undo") { gameRepository.insert(game) }
				setActionTextColor(Color.WHITE)
				show()
			}
		}
	}

	override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
		val view = viewHolder.itemView

		// draw the red background, based on the offset of the swipe (dX)
		backgroundColor.apply {
			setBounds(view.right + dX.toInt(), view.top, view.right, view.bottom)
			draw(c)
		}

		// draw the symbol
		recycleBinDrawable?.apply {
			val xt = view.top + (view.bottom - view.top - recycleBinDrawable.intrinsicHeight) / 2
			setBounds(
					view.right - recycleBinDrawableMargin - recycleBinDrawable.intrinsicWidth,
					xt,
					view.right - recycleBinDrawableMargin,
					xt + recycleBinDrawable.intrinsicHeight
			)
			draw(c)
		}

		super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
	}
}