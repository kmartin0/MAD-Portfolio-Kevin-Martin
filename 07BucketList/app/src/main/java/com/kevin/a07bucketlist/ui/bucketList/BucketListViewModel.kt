package com.kevin.a07bucketlist.ui.bucketList

import android.app.Application
import android.arch.lifecycle.*
import com.kevin.a07bucketlist.model.BucketItem
import com.kevin.a07bucketlist.repository.BucketListRepository

class BucketListViewModel(application: Application) : AndroidViewModel(application) {
    val bucketList: LiveData<List<BucketItem>>
    private val bucketListRepository = BucketListRepository(application.applicationContext)

    init {
        bucketList = bucketListRepository.getBucketList()
    }

    fun removeBucketListItem(bucketItem: BucketItem) {
        bucketListRepository.delete(bucketItem)
    }

    fun updateBucketListItem(bucketItem: BucketItem) {
        bucketListRepository.update(bucketItem)
    }

}