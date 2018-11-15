package com.kevin.a06recipeapp.api

import com.kevin.a06recipeapp.model.RecipeDetails
import com.kevin.a06recipeapp.model.TopRatedRecipesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface Food2ForkApiService {

    @GET("get/?key=${Food2ForkApi.polyApiKey}")
    fun getRecipeDetails(@Path("rId") rId: String): Single<RecipeDetails>

    @GET("search/?key=${Food2ForkApi.polyApiKey}")
    fun getTopRatedRecipes(): Single<TopRatedRecipesResponse>

}