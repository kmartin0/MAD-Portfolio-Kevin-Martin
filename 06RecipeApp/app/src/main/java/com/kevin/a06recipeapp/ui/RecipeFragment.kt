package com.kevin.a06recipeapp.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.kevin.a06recipeapp.R
import com.kevin.a06recipeapp.api.Food2ForkApi
import com.kevin.a06recipeapp.api.Food2ForkApiService
import com.kevin.a06recipeapp.model.Recipe
import com.kevin.a06recipeapp.model.RecipeDetailsResponse
import com.kevin.a06recipeapp.util.RECIPE_INTENT_EXTRA
import com.kevin.a06recipeapp.util.SnackBarHelper
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_recipe.*

class RecipeFragment : Fragment() {

    private lateinit var food2ForkApiService: Food2ForkApiService
    private var disposable: Disposable? = null

    companion object {
        fun newInstance(recipe: Recipe): RecipeFragment {
            val myFragment = RecipeFragment()

            val args = Bundle()
            args.putParcelable(RECIPE_INTENT_EXTRA, recipe)
            myFragment.arguments = args

            return myFragment
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_recipe, container, false)

    /**
     * Load the recipe details from the Food2Fork Api when the view is created.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        food2ForkApiService = Food2ForkApi.create()
        val recipe = arguments!!.get(RECIPE_INTENT_EXTRA) as Recipe?
        recipe?.let { getRecipeDetails(it) }
    }

    /**
     * Get the recipe details for the @recipe from the Food2Fork Api.
     */
    private fun getRecipeDetails(recipe: Recipe) {
        food2ForkApiService.getRecipeDetails(recipe.recipeId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<RecipeDetailsResponse> {
                    override fun onSuccess(t: RecipeDetailsResponse) {
                        when (t.error) {
                            null -> {
                                tvRecipeIngredients.text = parseIngredientsListToString(t.recipe.ingredients)
                                Glide.with(this@RecipeFragment).load(recipe.imageUrl).into(ivRecipe)
                                tvRecipeTitle.text = recipe.title
                                SnackBarHelper.showSnackBarMessage(activity as AppCompatActivity, "Successfully Retrieved Recipe Details")
                            }
                            else -> SnackBarHelper.showSnackBarMessage(activity as AppCompatActivity, "Error: ${t.error}")
                        }
                    }

                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                        SnackBarHelper.showSnackBarMessage(activity as AppCompatActivity, "Loading Recipe Details...")
                    }

                    override fun onError(e: Throwable) {
                        SnackBarHelper.showSnackBarMessage(activity as AppCompatActivity, "Error: ${e.message}")
                        e.printStackTrace()
                    }

                })
    }

    /**
     * Add a new line after each ingredient
     */
    private fun parseIngredientsListToString(ingredients: List<String>): String {
        var tmpIngredients = ""
        for (ingredient in ingredients) {
            tmpIngredients += "\t-\t$ingredient \n"
        }
        return tmpIngredients
    }

    override fun onStop() {
        super.onStop()
        disposable?.dispose()
    }

}