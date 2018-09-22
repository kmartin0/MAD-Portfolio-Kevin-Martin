package com.kevin.a03studentportal.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Patterns
import android.webkit.URLUtil
import android.widget.Toast
import com.kevin.a03studentportal.R
import com.kevin.a03studentportal.model.Portal
import com.kevin.a03studentportal.util.EXTRA_PORTAL_DESCRIPTION
import kotlinx.android.synthetic.main.activity_add_portal.*

class AddPortalActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_add_portal)
		initToolbar()
		btn_add_portal.setOnClickListener { onAddPortalClick() }
	}

	private fun initToolbar() {
		val toolbar = toolbar as Toolbar;
		toolbar.title = "Add Portal"
		toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
		setSupportActionBar(toolbar)
		toolbar.setNavigationOnClickListener { finish() }
	}

	/**
	 * Checks if fields are filled in and if the url is valid.
	 * Returns to previous activity with the Portal object as extra if all fields are valid.
	 */
	private fun onAddPortalClick() {
		when {
			et_title.text!!.isBlank() || et_url.text!!.isBlank() -> Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_LONG).show()
			!Patterns.WEB_URL.matcher(et_url.text.toString()).matches() -> Toast.makeText(this, "Url is not valid", Toast.LENGTH_LONG).show()
			else -> {
				val result = Intent()
				result.putExtra(EXTRA_PORTAL_DESCRIPTION, Portal(et_url.text.toString(), et_title.text.toString()))
				setResult(Activity.RESULT_OK, result)
				finish()
			}
		}
	}
}

