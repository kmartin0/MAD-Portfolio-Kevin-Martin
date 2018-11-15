package com.kevin.a06recipeapp.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.kevin.a06recipeapp.model.Recipe

class RecipePagerAdapter(fm: FragmentManager, private val recipes: List<Recipe>) : FragmentStatePagerAdapter(fm) {
	override fun getItem(position: Int): Fragment {
		return RecipeFragment.newInstance(recipes[position])
	}

	override fun getCount(): Int {
		return recipes.size
	}

}