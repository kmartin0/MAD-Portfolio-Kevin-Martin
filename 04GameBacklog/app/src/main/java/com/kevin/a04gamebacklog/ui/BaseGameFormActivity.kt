package com.kevin.a04gamebacklog.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.kevin.a04gamebacklog.R
import com.kevin.a04gamebacklog.database.GameRepository
import com.kevin.a04gamebacklog.model.Game
import com.kevin.a04gamebacklog.util.GAME_EXTRA
import com.kevin.a04gamebacklog.util.showSnackbar
import kotlinx.android.synthetic.main.activity_add_game.*
import java.text.SimpleDateFormat
import java.util.*

abstract class BaseGameFormActivity : AppCompatActivity() {

    protected lateinit var spinnerAdapter: ArrayAdapter<String>
    protected var bundle: Bundle? = null
    protected lateinit var gameRepository: GameRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_game)
        title = "Game Backlog Manager"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        gameRepository = GameRepository(this)
        bundle = intent.extras

        spinnerAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, statusArr)
        fab_save_game.setOnClickListener { onSaveClick() }
        spin_status.adapter = spinnerAdapter
    }

    private fun onSaveClick() {
        if (isFormFilledIn()) {
            val game = Game(et_title.text.toString(),
                    et_platform.text.toString(),
                    et_notes.text.toString(),
                    spin_status.selectedItem as String,
                    SimpleDateFormat("dd/MM/yyy", Locale("nl-NL")).format(Date()))

            if (bundle?.getSerializable(GAME_EXTRA) != null) game.gameId = (bundle?.getSerializable(GAME_EXTRA) as Game).gameId

            onSave(game)
        }
    }

    private fun isFormFilledIn(): Boolean {
        return when {
            et_title.text.isNullOrBlank() -> {
                showSnackbar("Please fill in a title", cl_add_game); false
            }
            et_platform.text.isNullOrBlank() -> {
                showSnackbar("Please fill in a platform", cl_add_game); false
            }
            et_notes.text.isNullOrBlank() -> {
                showSnackbar("Please fill in your notes", cl_add_game); false
            }
            else -> true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    abstract fun onSave(game: Game)

    companion object {
        val statusArr = arrayOf(
                "Want to Play",
                "Playing",
                "Stalled",
                "Dropped"
        )
    }

}