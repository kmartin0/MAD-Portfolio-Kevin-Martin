package com.kevin.a06recipeapp.model

data class TopRatedRecipesResponse(
        val count : Int,
        val recipes : Array<Recipe>
)