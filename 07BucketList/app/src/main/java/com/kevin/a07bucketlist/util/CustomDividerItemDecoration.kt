package com.kevin.a07bucketlist.util

import android.app.Activity
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.kevin.a07bucketlist.R
import org.jetbrains.anko.dimen

class CustomDividerItemDecoration(private val activity : Activity) : RecyclerView.ItemDecoration() {

	override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
		if (view != null) {
			val position = parent?.getChildAdapterPosition(view)
			when (position){
				RecyclerView.NO_POSITION -> return
				else -> outRect?.bottom = activity.dimen(R.dimen.margin_small)
			}
		}
	}

}