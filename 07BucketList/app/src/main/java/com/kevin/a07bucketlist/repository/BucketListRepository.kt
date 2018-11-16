package com.kevin.a07bucketlist.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import com.kevin.a07bucketlist.dao.BucketListDao
import com.kevin.a07bucketlist.database.BucketListRoomDatabase
import com.kevin.a07bucketlist.model.BucketItem
import org.jetbrains.anko.doAsync

class BucketListRepository(context: Context) {
	private var bucketListDao: BucketListDao

	init {
		val gameRoomDatabase = BucketListRoomDatabase.getDatabase(context)
		bucketListDao = gameRoomDatabase!!.bucketListDao()
	}

	fun getBucketList(): LiveData<List<BucketItem>> {
		return bucketListDao.getBucketList()
	}

	fun insert(bucketItem: BucketItem) {
		doAsync {
			bucketListDao.insert(bucketItem)
		}
	}

	fun delete(bucketItem: BucketItem) {
		doAsync {
			bucketListDao.delete(bucketItem)
		}
	}

	fun clearBucketList() {
		doAsync {
			bucketListDao.clearBucketList()
		}
	}

	fun update(bucketItem: BucketItem) {
		doAsync {
			bucketListDao.update(bucketItem)
		}
	}
}