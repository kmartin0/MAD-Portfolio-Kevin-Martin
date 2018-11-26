package com.kevin.a07bucketlist.ui.createItem

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.kevin.a07bucketlist.R
import com.kevin.a07bucketlist.base.BaseActivity
import com.kevin.a07bucketlist.databinding.ActivityCreateItemBinding
import com.kevin.a07bucketlist.util.SnackBarHelper

class CreateItemActivity : BaseActivity<ActivityCreateItemBinding, CreateItemViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.doneCallback.observe(this, Observer { finish() })
        viewModel.error.observe(this, Observer { displayMessage(it) })
    }

    private fun displayMessage(message: String?) {
        if (!message.isNullOrEmpty())
            SnackBarHelper.showSnackBarMessage(this@CreateItemActivity, message!!)
    }

    override fun getLayoutId(): Int = R.layout.activity_create_item

    override fun initViewModelBinding() {
        binding.viewModel = viewModel
    }

    override fun getVMClass(): Class<CreateItemViewModel> = CreateItemViewModel::class.java

    override fun getActivityTitle(): String = "Create Item"

}