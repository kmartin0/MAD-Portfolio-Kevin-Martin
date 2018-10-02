package com.kevin.a04gamebacklog.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.kevin.a04gamebacklog.model.Game

@Database(entities = [Game::class], version = 1, exportSchema = false)
abstract class GameRoomDatabase : RoomDatabase() {
	companion object {
		@Volatile
		private var gameRoomDatabaseInstance: GameRoomDatabase? = null

		fun getDatabase(context: Context): GameRoomDatabase? {
			if (gameRoomDatabaseInstance == null) {
				synchronized(GameRoomDatabase::class.java) {
					if (gameRoomDatabaseInstance == null) {
						gameRoomDatabaseInstance = Room.databaseBuilder(context.applicationContext,
								GameRoomDatabase::class.java, "game_database")
								.build()
					}
				}
			}
			return gameRoomDatabaseInstance
		}
	}

	abstract fun gameDao(): GameDao
}

