package com.kevin.a07bucketlist.ui.bucketList

import android.app.Application
import android.arch.lifecycle.*
import android.util.Log
import com.kevin.a07bucketlist.model.BucketItem
import com.kevin.a07bucketlist.repository.BucketListRepository

class BucketListViewModel(application: Application) : AndroidViewModel(application) {
	val bucketList: LiveData<List<BucketItem>>
	private val bucketListRepository = BucketListRepository(application.applicationContext)

	init {
		bucketList = bucketListRepository.getBucketList()

		bucketListRepository.apply {
			insert(BucketItem(false, "Roadtrip", "Roadtrip through America"))
			insert(BucketItem(false, "Paris", "walking from the Netherlands to Paris"))
			insert(BucketItem(true, "Party", "Go to the biggest party in Ibiza"))
		}

	}

	fun removeBucketListItem(bucketItem: BucketItem) {
		bucketListRepository.delete(bucketItem)
	}

	fun insertBucketListItem(bucketItem: BucketItem) {
		bucketListRepository.insert(bucketItem)
	}

	fun updateBucketListItem(bucketItem: BucketItem) {
		Log.i("TAGZ__", "Update ${bucketItem.bucketItemId} $bucketItem")
		bucketListRepository.update(bucketItem)
	}

}