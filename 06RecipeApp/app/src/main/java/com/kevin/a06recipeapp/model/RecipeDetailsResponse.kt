package com.kevin.a06recipeapp.model

import com.google.gson.annotations.SerializedName

data class RecipeDetailsResponse(
		@SerializedName("recipe") val recipe: RecipeDetails,
		val error: String?)