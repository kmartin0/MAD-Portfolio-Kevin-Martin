package com.kevin.a06recipeapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe(
		@SerializedName("publisher") val publisher: String,
		@SerializedName("f2f_url") val f2fUrl: String,
		@SerializedName("title") val title: String,
		@SerializedName("source_url") val sourceUrl: String,
		@SerializedName("recipe_id") val recipeId: String,
		@SerializedName("image_url") val imageUrl: String,
		@SerializedName("social_rank") val socialRank: String,
		@SerializedName("publisher_url") val publisherUrl: String
) : Parcelable
