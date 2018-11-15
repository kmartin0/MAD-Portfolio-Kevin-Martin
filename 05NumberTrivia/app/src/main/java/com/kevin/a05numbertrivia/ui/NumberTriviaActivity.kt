package com.kevin.a05numbertrivia.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.view.View
import android.widget.LinearLayout.VERTICAL
import com.kevin.a05numbertrivia.R
import com.kevin.a05numbertrivia.api.NumbersApi
import com.kevin.a05numbertrivia.model.Number
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_numbers_trivia.*

class NumberTriviaActivity : AppCompatActivity() {

	private val numbersApi by lazy { NumbersApi.create() }
	private var disposable: Disposable? = null
	private val numbersAdapter by lazy { NumbersAdapter() }
	private var numbersList = ArrayList<Number>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_numbers_trivia)
		title = getString(R.string.trivia_title)

		rv_numbers.adapter = numbersAdapter
		rv_numbers.addItemDecoration(DividerItemDecoration(this, VERTICAL))

		fab_add.setOnClickListener { getRandomNumber() }
	}

	/**
	 * Fetches a Number from the Numbers Api and adds it to the recyclerview.
	 */
	private fun getRandomNumber() {
		disposable = numbersApi.getRandomNumberTrivia()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.doOnSubscribe { loading_circle.visibility = View.VISIBLE }
				.doOnTerminate { loading_circle.visibility = View.GONE }
				.subscribe(
						{ updateNumbersList(it) },
						{ showSnackbar(it.message.toString()) }
				)
	}

	/**
	 * Adds a Number object to the numbersList and the recyclerview adapter.
	 * @param number number to be added to the numbersList and the recyclerview.
	 */
	private fun updateNumbersList(number: Number) {
		numbersList.add(number)
		numbersAdapter.updateNumbers(numbersList)
		rv_numbers.scrollToPosition(numbersAdapter.itemCount - 1)
	}

	/**
	 * Displays a snackbar message
	 */
	private fun showSnackbar(message: String) {
		Snackbar.make(fab_add, message, Snackbar.LENGTH_LONG).show()
	}

	override fun onPause() {
		super.onPause()
		disposable?.dispose()
	}

}