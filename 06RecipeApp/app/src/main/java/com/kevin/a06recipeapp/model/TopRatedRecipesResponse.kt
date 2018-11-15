package com.kevin.a06recipeapp.model

data class TopRatedRecipesResponse(
		val count: Int,
		val recipes: List<Recipe>?,
		val error: String?
)