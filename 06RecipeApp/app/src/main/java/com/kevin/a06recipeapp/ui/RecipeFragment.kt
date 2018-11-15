package com.kevin.a06recipeapp.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.kevin.a06recipeapp.R
import com.kevin.a06recipeapp.api.Food2ForkApi
import com.kevin.a06recipeapp.api.Food2ForkApiService
import com.kevin.a06recipeapp.model.Recipe
import com.kevin.a06recipeapp.model.RecipeDetails
import com.kevin.a06recipeapp.model.TopRatedRecipesResponse
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_recipes.*
import kotlinx.android.synthetic.main.fragment_recipe.*

class RecipeFragment : Fragment() {

    private lateinit var food2ForkApiService : Food2ForkApiService

    companion object {
        fun newInstance(recipe: Recipe): RecipeFragment {
            val myFragment = RecipeFragment()

            val args = Bundle()
            args.putSerializable("recipe", recipe)
            myFragment.arguments = args

            return myFragment
        }
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_recipe, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recipe = arguments!!.get("recipe") as Recipe
        Glide.with(this).load(recipe.imageUrl).into(ivRecipe)
        tvRecipeTitle.text = recipe.title

        food2ForkApiService = Food2ForkApi.create()
        food2ForkApiService.getRecipeDetails(recipe.recipeId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<RecipeDetails> {
                    override fun onSuccess(t: RecipeDetails) {
                        tvRecipeIngredients.text = t.ingredients[0]
                        Snackbar.make(vpRecipes, "Success!!", Snackbar.LENGTH_LONG).show()
                    }

                    override fun onSubscribe(d: Disposable) {
                        Snackbar.make(vpRecipes, "Subscribing...", Snackbar.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        Snackbar.make(vpRecipes, "Error: ${e.message}", Snackbar.LENGTH_LONG).show()
                        e.printStackTrace()
                    }

                })
    }

}