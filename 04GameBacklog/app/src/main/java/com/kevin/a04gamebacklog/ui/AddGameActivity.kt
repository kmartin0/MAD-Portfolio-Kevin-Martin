package com.kevin.a04gamebacklog.ui

import com.kevin.a04gamebacklog.model.Game

class AddGameActivity : BaseGameFormActivity() {
	override fun onSave(game: Game) {
		gameRepository.insert(game)
		finish()
	}
}
