package com.kevin.a06recipeapp.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.Log
import com.kevin.a06recipeapp.R
import com.kevin.a06recipeapp.api.Food2ForkApi
import com.kevin.a06recipeapp.api.Food2ForkApiService
import com.kevin.a06recipeapp.model.Recipe
import com.kevin.a06recipeapp.model.TopRatedRecipesResponse
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.internal.operators.single.SingleObserveOn
import io.reactivex.internal.schedulers.IoScheduler
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_recipes.*

private const val NUM_PAGES = 5

class RecipesActivity : FragmentActivity() {

    private var recipes = ArrayList<Recipe>()
    private lateinit var food2ForkApiService: Food2ForkApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)
        title = "Recipes"

        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        vpRecipes.adapter = pagerAdapter

        food2ForkApiService = Food2ForkApi.create()
        food2ForkApiService.getTopRatedRecipes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<TopRatedRecipesResponse> {
                    override fun onSuccess(t: TopRatedRecipesResponse) {
                        recipes = t.recipes.toCollection(ArrayList())
                        pagerAdapter.notifyDataSetChanged()
                        Snackbar.make(vpRecipes, "Success!! ${t.recipes.size} ${t.count}", Snackbar.LENGTH_LONG).show()
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

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = recipes.size

//        override fun getItem(position: Int): Fragment = RecipeFragment.newInstance(
//                recipes[position].title,
//                recipes[position].publisherUrl,
//                recipes[position].page
//        )

        override fun getItem(position: Int): Fragment {
            Log.i("TAGZ__", "Recipe: ${recipes[position]}")
            return RecipeFragment.newInstance(
                    recipes[position])
        }

    }

}

