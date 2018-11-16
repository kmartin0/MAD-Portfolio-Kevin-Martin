package com.kevin.a07bucketlist.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.kevin.a07bucketlist.model.BucketItem

@Dao
interface BucketListDao {
	@Insert
	fun insert(bucketItem: BucketItem)

	@Delete
	fun delete(bucketItem: BucketItem)

	@Update
	fun update(bucketItem: BucketItem)

	@Query("SELECT * from bucket_list_table")
	fun getBucketList(): LiveData<List<BucketItem>>

	@Query("DELETE from bucket_list_table")
	fun clearBucketList()
}