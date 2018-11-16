package com.kevin.a07bucketlist.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BucketListItem(
		val done: Boolean,
		val title: String,
		val description: String
) : Parcelable