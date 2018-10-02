package com.kevin.a04gamebacklog.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.widget.LinearLayout
import com.kevin.a04gamebacklog.R
import com.kevin.a04gamebacklog.database.GameRepository
import com.kevin.a04gamebacklog.model.Game
import com.kevin.a04gamebacklog.util.GAME_EXTRA
import com.kevin.a04gamebacklog.util.GameAdapterSwipeCallback
import kotlinx.android.synthetic.main.activity_game_backlog.*

class GameBacklogActivity : AppCompatActivity() {

	private val gamesAdapter = GamesAdapter { if (it != null) onEditGameClick(it) }
	private lateinit var gamesList: LiveData<List<Game>>
	private lateinit var gameRepository: GameRepository

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_game_backlog)
		title = "Game Backlog Manager"
		initGameRepository()
		initRecyclerView()
		fab_add_game.setOnClickListener { onAddGameClick() }
	}

	private fun initRecyclerView() {
		rv_games.apply {
			layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false).apply {
				reverseLayout = true
				stackFromEnd = true
			}
			adapter = gamesAdapter
		}
		val swipeCallback = GameAdapterSwipeCallback(cl_fab, gamesAdapter, gameRepository, this)
		ItemTouchHelper(swipeCallback).attachToRecyclerView(rv_games)
	}

	private fun initGameRepository() {
		gameRepository = GameRepository(this)
		gamesList = gameRepository.getAllGames()
		gamesList.observe(this, Observer {
			gamesAdapter.updateGames(it)
			Log.i("TAGZ", "Update: $it")
		})
	}

	private fun onAddGameClick() {
		startActivity(Intent(this, AddGameActivity::class.java))
	}

	private fun onEditGameClick(game: Game) {
		val intent = Intent(this, EditGameActivity::class.java)
		intent.putExtra(GAME_EXTRA, game)
		startActivity(intent)
	}
}