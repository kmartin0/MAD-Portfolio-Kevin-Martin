package com.kevin.a04gamebacklog.database

import android.arch.lifecycle.LiveData
import android.content.Context
import com.kevin.a04gamebacklog.model.Game
import org.jetbrains.anko.doAsync

class GameRepository(context: Context) {
	private var gameDao: GameDao

	init {
		val gameRoomDatabase = GameRoomDatabase.getDatabase(context)
		gameDao = gameRoomDatabase!!.gameDao()
	}

	fun getAllGames(): LiveData<List<Game>> {
		return gameDao.getAllGames()
	}

	fun insert(game: Game) {
		doAsync {
			gameDao.insert(game)
		}
	}

	fun delete(game: Game) {
		doAsync {
			gameDao.delete(game)
		}
	}

	fun deleteAll() {
		doAsync {
			gameDao.removeAllGames()
		}
	}

	fun update(game: Game) {
		doAsync {
			gameDao.update(game)
		}
	}
}