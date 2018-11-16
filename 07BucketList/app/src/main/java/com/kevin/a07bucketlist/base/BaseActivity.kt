package com.kevin.a07bucketlist.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity<T : ViewDataBinding, V : ViewModel>() : AppCompatActivity() {
	protected lateinit var binding: T
	protected lateinit var viewModel: V

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = DataBindingUtil.setContentView(this, getLayoutId())
		viewModel = ViewModelProviders.of(this).get(getVMClass())
		initViewModelBinding()
		title = getActivityTitle()
	}

	@LayoutRes
	abstract fun getLayoutId(): Int

	abstract fun initViewModelBinding()

	abstract fun getVMClass(): Class<V>

	abstract fun getActivityTitle(): String
}