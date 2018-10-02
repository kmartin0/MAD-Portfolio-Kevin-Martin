package com.kevin.a04gamebacklog.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.kevin.a04gamebacklog.model.Game

@Dao
interface GameDao {
	@Insert
	fun insert(game: Game)

	@Delete
	fun delete(game: Game)

	@Update
	fun update(game: Game)

	@Query("SELECT * from game_table")
	fun getAllGames(): LiveData<List<Game>>

	@Query("DELETE from game_table")
	fun removeAllGames()
}