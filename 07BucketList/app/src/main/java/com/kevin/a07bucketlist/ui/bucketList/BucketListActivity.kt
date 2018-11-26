package com.kevin.a07bucketlist.ui.bucketList

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.kevin.a07bucketlist.R
import com.kevin.a07bucketlist.base.BaseActivity
import com.kevin.a07bucketlist.databinding.ActivityBucketListBinding
import com.kevin.a07bucketlist.ui.createItem.CreateItemActivity
import com.kevin.a07bucketlist.util.CustomDividerItemDecoration
import com.kevin.a07bucketlist.util.SnackBarHelper
import kotlinx.android.synthetic.main.activity_bucket_list.*

class BucketListActivity : BaseActivity<ActivityBucketListBinding, BucketListViewModel>() {

    private val bucketListAdapter = BucketListAdapter(
            { viewModel.removeBucketListItem(it) },
            { viewModel.updateBucketListItem(it) }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rvBucketList.adapter = bucketListAdapter
        rvBucketList.addItemDecoration(CustomDividerItemDecoration(this))

        // Update the recycler view with the bucket list from the view model
        viewModel.bucketList.observe(this, Observer {
            bucketListAdapter.updateList(it)
            SnackBarHelper.showSnackBarMessage(this@BucketListActivity, "Bucket List Update ${it?.size}")
        })

        // Go to create item activity when the floating action button is clicked.
        fabAddBucketItem.setOnClickListener { startActivity(Intent(this@BucketListActivity, CreateItemActivity::class.java)) }
    }

    override fun getLayoutId(): Int = R.layout.activity_bucket_list

    override fun initViewModelBinding() {
        binding.viewModel = viewModel
    }

    override fun getVMClass(): Class<BucketListViewModel> = BucketListViewModel::class.java

    override fun getActivityTitle(): String = "Bucket List"
}