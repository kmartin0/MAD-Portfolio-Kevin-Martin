package com.kevin.a02geoguessswipe.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.helper.ItemTouchHelper
import com.kevin.a02geoguessswipe.R
import com.kevin.a02geoguessswipe.model.StreetView
import com.kevin.a02geoguessswipe.util.StreetViewListSwipeCallback
import kotlinx.android.synthetic.main.activity_geo_guess_swipe_activity.*


class GeoGuessSwipeActivity : AppCompatActivity() {

	private val streetViewListAdapter = StreetViewListAdapter()
	private val streetViewsList = createStreetViewArrayList()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_geo_guess_swipe_activity)
		supportActionBar?.title = "Geo Guess Swipe"
		initStreetViewSRecyclerView()
	}

	private fun initStreetViewSRecyclerView() {
		rv_street_views.adapter = streetViewListAdapter
		streetViewListAdapter.updatestreetViewDrawableResIds(streetViewsList)
		ItemTouchHelper(StreetViewListSwipeCallback(rv_street_views, streetViewListAdapter)).attachToRecyclerView(rv_street_views)
	}

	private fun createStreetViewArrayList(): ArrayList<StreetView> {
		return arrayListOf(
				StreetView("Denmark", R.drawable.img1_yes_denmark, true),
				StreetView("Canada", R.drawable.img2_no_canada, false),
				StreetView("Bangladesh", R.drawable.img3_no_bangladesh, false),
				StreetView("Kazakhstan", R.drawable.img4_yes_kazachstan, true),
				StreetView("Colombia", R.drawable.img5_no_colombia, false),
				StreetView("Poland", R.drawable.img6_yes_poland, true),
				StreetView("Malta", R.drawable.img7_yes_malta, true),
				StreetView("Thailand", R.drawable.img8_no_thailand, false)
		)
	}

}