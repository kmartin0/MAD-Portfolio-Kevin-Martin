package com.example.kevin.a01higherlower.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.example.kevin.a01higherlower.R
import com.example.kevin.a01higherlower.util.rand
import kotlinx.android.synthetic.main.activity_higher_lower.*
import kotlin.collections.ArrayList

class HigherLowerActivity : AppCompatActivity() {

	private var score: Int = 0
	private var highScore: Int = 0
	private var currentDice: Int = rand(1, 7)

	private var adapter = ThrowListAdapter()
	private var throwList = ArrayList<Int>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_higher_lower)
		init()
	}

	private fun init() {
		// Initialize the initial score view
		setScoreText(score)
		setHighScoreText(highScore)

		// Initialize the higher lower click listeners
		fab_down.setOnClickListener { onLowerClick() }
		fab_up.setOnClickListener { onHigherClick() }

		// Initialize the recycler view adapter
		rv_throws.adapter = adapter

		// Initialize the first dice view
		setDiceImage(currentDice)
	}


	private fun setScoreText(score: Int) {
		tv_score.text = resources.getString(R.string.score, score)
	}

	private fun setHighScoreText(highScore: Int) {
		tv_high_score.text = resources.getString(R.string.high_score, highScore)
	}

	/**
	 * Throws the dice and updates the dice image, if the result is higher than the previous dice
	 * then a point will be added to the current score and the user will be notified. If the dice is
	 * lower the user will be notified and the score will be reset.
	 */
	private fun onHigherClick() {
		val diceResult = generateDiceThrow()
		if (diceResult > currentDice) {
			onWin()
		} else {
			onLose()
		}

		currentDice = diceResult
		setDiceImage(currentDice)
		updateThrowHistoryWithCurrentDice()

	}

	/**
	 * Throws the dice and updates the dice image, if the result is lower than the previous dice
	 * then a point will be added to the current score and the user will be notified. If the dice is
	 * higher the user will be notified and the score will be reset.
	 */
	private fun onLowerClick() {
		val diceResult = generateDiceThrow()

		if (diceResult < currentDice) {
			onWin()
		} else {
			onLose()
		}

		currentDice = diceResult
		setDiceImage(currentDice)
		updateThrowHistoryWithCurrentDice()

	}

	/**
	 * Updates the throw history recycler view with the current throw history.
	 */
	private fun updateThrowHistoryWithCurrentDice() {
		throwList.add(currentDice)
		adapter.updateThrowList(throwList)
		rv_throws.scrollToPosition(throwList.size - 1)
	}

	/**
	 * Adds the image according to the dice number to the image view
	 * @diceNumber The number of the new dice.
	 */
	private fun setDiceImage(diceNumber: Int) {
		when (diceNumber) {
			1 -> iv_dice.setImageResource(R.drawable.d1)
			2 -> iv_dice.setImageResource(R.drawable.d2)
			3 -> iv_dice.setImageResource(R.drawable.d3)
			4 -> iv_dice.setImageResource(R.drawable.d4)
			5 -> iv_dice.setImageResource(R.drawable.d5)
			6 -> iv_dice.setImageResource(R.drawable.d6)
		}
	}

	/**
	 * Updates the score and the score text with a point added and shows a snackbar message.
	 */
	private fun onWin() {
		setScoreText(++score)
		Snackbar.make(iv_dice, "You Win", Snackbar.LENGTH_LONG).show()
	}

	/**
	 * Resets the score and the score text to zero. Updates the high score if necessary and shows a
	 * snackbar message.
	 */
	private fun onLose() {
		if (score > highScore) {
			highScore = score
			setHighScoreText(highScore)
		}

		score = 0
		setScoreText(score)

		Snackbar.make(iv_dice, "You Lose", Snackbar.LENGTH_LONG).show()
	}

	/**
	 * @return A number between 1 and 6 (inclusive) which isn't the same as the current dice.
	 */
	private fun generateDiceThrow(): Int {
		var diceResult = rand(1, 7)
		while (diceResult == currentDice) diceResult = rand(1, 7)
		return diceResult
	}

}