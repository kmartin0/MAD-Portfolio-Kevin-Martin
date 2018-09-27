package com.kevin.a04gamebacklog.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.kevin.a04gamebacklog.R
import com.kevin.a04gamebacklog.model.Game
import kotlinx.android.synthetic.main.activity_game_backlog.*

class GameBacklog : AppCompatActivity() {

    private val gamesAdapter = GamesAdapter()
    private val gamesList = ArrayList<Game>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_backlog)
        title = "Game Backlog Manager"

        gamesList.add(Game("The Witcher 3", "PC", "Saving Money", "Playing", "31/10/2017"))
        gamesList.add(Game("Overwatch", "PC", "Saving Money", "Dropped", "31/10/2017"))
        gamesList.add(Game("Destiny 2", "PS4", "Saving Money", "Stalled", "31/10/2017"))

        rv_games.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        rv_games.adapter = gamesAdapter
        gamesAdapter.updateGames(gamesList)
    }

}