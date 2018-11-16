package com.kevin.a07bucketlist.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "bucket_list_table")
data class BucketItem(
		var done: Boolean,
		val title: String,
		val description: String
) : Parcelable {
	@PrimaryKey(autoGenerate = true)
	var bucketItemId: Int = 0
}