package com.kevin.a03studentportal.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.kevin.a03studentportal.R
import com.kevin.a03studentportal.model.Portal
import com.kevin.a03studentportal.util.ADD_PORTAL_REQUEST_CODE
import com.kevin.a03studentportal.util.EXTRA_PORTAL_DESCRIPTION
import kotlinx.android.synthetic.main.activity_student_portal.*

class StudentPortalActivity : AppCompatActivity() {

	private val studentPortalsAdapter = StudentPortalsAdapter { portal -> onPortalTitleClick(portal) }
	private val portalList = ArrayList<Portal>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_student_portal)
		initToolbar()
		initPortalList()
		initClickListeners()
	}

	private fun initToolbar() {
		val toolbar = toolbar as Toolbar;
		toolbar.title = "Student Portal"
		setSupportActionBar(toolbar)
	}

	private fun initPortalList() {
		rv_portal_list.layoutManager = GridLayoutManager(this, 3)
		rv_portal_list.adapter = studentPortalsAdapter
		studentPortalsAdapter.updateStudentPortals(portalList)
	}

	private fun initClickListeners() {
		fab_new_portal.setOnClickListener { goToAddPortalActivity() }
	}

	private fun goToAddPortalActivity() {
		val intent = Intent(this, AddPortalActivity::class.java)
		startActivityForResult(intent, ADD_PORTAL_REQUEST_CODE)
	}

	private fun addPortalToList(portal: Portal) {
		portalList.add(portal)
		studentPortalsAdapter.updateStudentPortals(portalList)
	}

	private fun onPortalTitleClick(portal: Portal) {
		val intent = Intent(this, WebViewActivity::class.java)
		intent.putExtra(EXTRA_PORTAL_DESCRIPTION, portal)
		startActivity(intent)
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		if (requestCode == ADD_PORTAL_REQUEST_CODE && resultCode == RESULT_OK) {
			val portal = data?.getSerializableExtra(EXTRA_PORTAL_DESCRIPTION) as Portal
			addPortalToList(portal)
			Toast.makeText(this, "Successfully added ${portal.title}", Toast.LENGTH_LONG).show()
		}

	}

}