package com.kevin.a06recipeapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipeDetails(
		@SerializedName("publisher") var publisher: String,
		@SerializedName("f2f_url") var f2fUrl: String,
		@SerializedName("ingredients") var ingredients: List<String>,
		@SerializedName("source_url") var sourceUrl: String,
		@SerializedName("recipe_id") var recipeId: String,
		@SerializedName("image_url") var imageUrl: String,
		@SerializedName("social_rank") var socialRank: String,
		@SerializedName("publisher_url") var publisherUrl: String,
		@SerializedName("title") var title: String
) : Parcelable
