package com.kevin.a06recipeapp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kevin.a06recipeapp.R
import com.kevin.a06recipeapp.util.SnackBarHelper
import com.kevin.a06recipeapp.api.Food2ForkApi
import com.kevin.a06recipeapp.api.Food2ForkApiService
import com.kevin.a06recipeapp.model.Recipe
import com.kevin.a06recipeapp.model.TopRatedRecipesResponse
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_recipes.*

class RecipesActivity : AppCompatActivity() {

	private var recipes = ArrayList<Recipe>()
	private lateinit var food2ForkApiService: Food2ForkApiService
	private lateinit var pagerAdapter : RecipePagerAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_recipes)
		supportActionBar?.title = getString(R.string.app_name)

		// Initialize ViewPager
		pagerAdapter = RecipePagerAdapter(supportFragmentManager, recipes)
		vpRecipes.adapter = pagerAdapter

		// Initialize Api Service
		food2ForkApiService = Food2ForkApi.create()
		loadTopRatedRecipes()

	}

	/**
	 * Get the Top Rated Recipes from the Food2Fork Api. If the recipes were retrieved successfully
	 * add the recipes to the viewpager.
	 */
	private fun loadTopRatedRecipes(){
		food2ForkApiService.getTopRatedRecipes()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(object : SingleObserver<TopRatedRecipesResponse> {
					override fun onSuccess(t: TopRatedRecipesResponse) {
						t.recipes?.let {
							recipes.addAll(it)
							pagerAdapter.notifyDataSetChanged()
						}
						when (t.error) {
							null -> SnackBarHelper.showSnackBarMessage(this@RecipesActivity, "Successfully retrieved top rated recipes")
							else -> SnackBarHelper.showSnackBarMessage(this@RecipesActivity, "Error: ${t.error}")
						}
					}

					override fun onSubscribe(d: Disposable) {
						SnackBarHelper.showSnackBarMessage(this@RecipesActivity, "Retrieving top rated recipes")
					}

					override fun onError(e: Throwable) {
						SnackBarHelper.showSnackBarMessage(this@RecipesActivity, "Error: ${e.message}")
						e.printStackTrace()
					}

				})
	}

	override fun onBackPressed() {
		if (vpRecipes.currentItem == 0) {
			// If the user is currently looking at the first step, allow the system to handle the
			// Back button. This calls finish() on this activity and pops the back stack.
			super.onBackPressed()
		} else {
			// Otherwise, select the previous step.
			vpRecipes.currentItem = vpRecipes.currentItem - 1
		}
	}
}

