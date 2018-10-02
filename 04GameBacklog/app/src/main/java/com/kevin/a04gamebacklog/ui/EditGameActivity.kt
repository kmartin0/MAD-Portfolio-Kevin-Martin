package com.kevin.a04gamebacklog.ui

import android.os.Bundle
import android.widget.TextView
import com.kevin.a04gamebacklog.model.Game
import com.kevin.a04gamebacklog.util.GAME_EXTRA
import kotlinx.android.synthetic.main.activity_add_game.*

class EditGameActivity : BaseGameFormActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		populateForm(bundle?.get(GAME_EXTRA) as Game)
	}

	override fun onSave(game: Game) {
		gameRepository.update(game)
		finish()
	}

	private fun populateForm(game: Game) {
		et_title.setText(game.title, TextView.BufferType.EDITABLE)
		et_platform.setText(game.platform, TextView.BufferType.EDITABLE)
		et_notes.setText(game.notes, TextView.BufferType.EDITABLE)
		spin_status.setSelection(spinnerAdapter.getPosition(game.status))
	}

}