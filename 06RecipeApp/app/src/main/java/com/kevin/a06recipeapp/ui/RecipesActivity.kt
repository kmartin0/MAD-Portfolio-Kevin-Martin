package com.kevin.a06recipeapp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kevin.a06recipeapp.R

class RecipesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)
        title = "Recipes"
    }

}

