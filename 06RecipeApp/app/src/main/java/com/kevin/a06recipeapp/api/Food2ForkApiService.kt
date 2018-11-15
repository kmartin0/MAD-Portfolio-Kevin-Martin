package com.kevin.a06recipeapp.api

import com.kevin.a06recipeapp.model.RecipeDetailsResponse
import com.kevin.a06recipeapp.model.TopRatedRecipesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Food2ForkApiService {

	@GET("get/?key=${Food2ForkApi.polyApiKey}")
	fun getRecipeDetails(@Query("rId") rId: String): Single<RecipeDetailsResponse>

	@GET("search/?key=${Food2ForkApi.polyApiKey}")
	fun getTopRatedRecipes(): Single<TopRatedRecipesResponse>

}