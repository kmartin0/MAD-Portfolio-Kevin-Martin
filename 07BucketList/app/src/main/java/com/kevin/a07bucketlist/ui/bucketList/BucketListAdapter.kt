package com.kevin.a07bucketlist.ui.bucketList

import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.kevin.a07bucketlist.R
import com.kevin.a07bucketlist.model.BucketItem
import kotlinx.android.synthetic.main.item_bucket_list.view.*


class BucketListAdapter(private val deleteCallback: (BucketItem) -> Unit,
						private val updateCallback: (BucketItem) -> Unit) : RecyclerView.Adapter<BucketListAdapter.ViewHolder>() {

	private var bucketItemList: List<BucketItem>? = null

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(
				LayoutInflater.from(parent.context)
						.inflate(R.layout.item_bucket_list, parent, false)
		)
	}

	override fun getItemCount(): Int = bucketItemList?.size ?: 0

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.reset()
		bucketItemList?.get(position)?.let { item ->
			holder.checkBoxBucketItem.isChecked = item.done
			holder.tvBucketItemTitle.text = item.title
			holder.tvBucketItemDescription.text = item.description

			// Sets the strike through text if the item is done.
			if (item.done) {
				holder.tvBucketItemTitle.paintFlags = holder.tvBucketItemTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
				holder.tvBucketItemDescription.paintFlags = holder.tvBucketItemDescription.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
			} else {
				holder.tvBucketItemTitle.paintFlags = holder.tvBucketItemTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
				holder.tvBucketItemDescription.paintFlags = holder.tvBucketItemDescription.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
			}

			holder.btnRemove.setOnClickListener { deleteCallback(item) }
			holder.checkBoxBucketItem.setOnCheckedChangeListener { _, isChecked ->
				item.done = isChecked
				updateCallback(item)
			}
		}
	}

	fun updateList(bucketItemList: List<BucketItem>?) {
		this.bucketItemList = bucketItemList
		notifyDataSetChanged()
	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		val checkBoxBucketItem: CheckBox = itemView.checkBoxBucketItem
		val tvBucketItemTitle: TextView = itemView.tvBucketItemTitle
		val tvBucketItemDescription: TextView = itemView.tvBucketItemDescription
		val btnRemove: ImageView = itemView.btnRemove

		fun reset() {
			checkBoxBucketItem.setOnCheckedChangeListener(null)
			btnRemove.setOnClickListener(null)
		}

	}

}