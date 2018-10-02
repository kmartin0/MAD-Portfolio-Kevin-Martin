package com.kevin.a04gamebacklog.util

import android.support.design.widget.Snackbar
import android.view.View

fun showSnackbar(message: String, view: View) {
	Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
}