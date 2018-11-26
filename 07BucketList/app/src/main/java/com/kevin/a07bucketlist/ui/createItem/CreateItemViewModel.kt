package com.kevin.a07bucketlist.ui.createItem

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.kevin.a07bucketlist.model.BucketItem
import com.kevin.a07bucketlist.repository.BucketListRepository
import com.kevin.a07bucketlist.util.SingleLiveEvent

class CreateItemViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = BucketListRepository(application.applicationContext)

    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val doneCallback = SingleLiveEvent<Boolean>()
    val error = MutableLiveData<String>()

    /**
     * Creates and inserts the bucket item from the @title and @description
     * if they are not null or empty.
     */
    fun onAddClick() {
        validateForm()
        if (error.value.isNullOrEmpty()) {
            repository.insert(BucketItem(false, title.value!!, description.value!!))
            doneCallback.call()
        }
    }

    /**
     * Checks if the @title and @description are filled in. Sets the @error value if it's empty.
     */
    private fun validateForm() {
        when {
            title.value.isNullOrEmpty() -> {
                error.value = "Please fill in the title"
                return
            }
            description.value.isNullOrEmpty() -> {
                error.value = "Please fill in the description"
                return
            }
            else -> error.value = null
        }
    }
}