package com.kevin.a03studentportal.ui

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.kevin.a03studentportal.R
import kotlinx.android.synthetic.main.activity_web_view.*
import android.util.Log
import android.webkit.*
import android.widget.Toast
import com.kevin.a03studentportal.model.Portal
import com.kevin.a03studentportal.util.EXTRA_PORTAL_DESCRIPTION


class WebViewActivity : AppCompatActivity() {

	private lateinit var portal: Portal

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_web_view)
		portal = intent.getSerializableExtra(EXTRA_PORTAL_DESCRIPTION) as Portal
		initToolbar()
		initWebView()
	}

	@SuppressLint("SetJavaScriptEnabled")
	private fun initWebView() {
		web_view.webViewClient = WebViewClient()
		web_view.settings.javaScriptEnabled = true
		if (portal.url.startsWith("http://") || portal.url.startsWith("https://")) web_view.loadUrl(portal.url)
		else web_view.loadUrl("http://${portal.url}")
	}

	private fun initToolbar() {
		val toolbar = toolbar as Toolbar;
		toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
		toolbar.title = "Student Portal"
		setSupportActionBar(toolbar)
		toolbar.setNavigationOnClickListener { finish() }
	}
}