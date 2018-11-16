package com.kevin.a07bucketlist.ui.bucketList

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.kevin.a07bucketlist.R
import com.kevin.a07bucketlist.model.BucketListItem
import kotlinx.android.synthetic.main.item_bucket_list.view.*

class BucketListAdapter : RecyclerView.Adapter<BucketListAdapter.ViewHolder>() {

	private var bucketItemList: List<BucketListItem>? = null

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(
				LayoutInflater.from(parent.context)
						.inflate(R.layout.item_bucket_list, parent, false)
		)
	}

	override fun getItemCount(): Int = bucketItemList?.size ?: 0

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val bucketItem = bucketItemList?.get(position)
		bucketItem?.let {
			holder.checkBoxBucketItem.isChecked = it.done
			holder.tvBucketItemTitle.text = it.title
			holder.tvBucketItemDescription.text = it.description
		}
	}

	fun updateList(bucketItemList: List<BucketListItem>?) {
		this.bucketItemList = bucketItemList
		notifyDataSetChanged()
	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		val checkBoxBucketItem: CheckBox = itemView.checkBoxBucketItem
		val tvBucketItemTitle: TextView = itemView.tvBucketItemTitle
		val tvBucketItemDescription: TextView = itemView.tvBucketItemDescription
	}

}