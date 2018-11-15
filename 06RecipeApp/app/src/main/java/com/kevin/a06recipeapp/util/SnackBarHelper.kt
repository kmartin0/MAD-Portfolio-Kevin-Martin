package com.kevin.a06recipeapp.util

import android.app.Activity
import android.support.design.widget.Snackbar

class SnackBarHelper {
	companion object {
		fun showSnackBarMessage(activity: Activity, message: String) {
			Snackbar.make(activity.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show()
		}
	}
}