package com.kevin.a07bucketlist.ui.bucketList

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.kevin.a07bucketlist.model.BucketListItem

class BucketListViewModel : ViewModel() {
	val bucketList: MutableLiveData<List<BucketListItem>> = MutableLiveData()

	init {
		bucketList.value = arrayListOf(
				BucketListItem(false, "Roadtrip", "Roadtrip through America"),
				BucketListItem(false, "Paris", "walking from the Netherlands to Paris"),
				BucketListItem(true, "Party", "Go to the biggest party in Ibiza")
		)
	}

}