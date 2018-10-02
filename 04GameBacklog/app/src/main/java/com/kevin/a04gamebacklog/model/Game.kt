package com.kevin.a04gamebacklog.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "game_table")
data class Game(
		var title: String,
		var platform: String,
		var notes: String,
		var status: String,
		var date: String
) : Serializable {

	@PrimaryKey(autoGenerate = true)
	var gameId: Int = 0

//	enum class GameStatus(val status: String) {
//		WANT_TO_PLAY("Want to Play"),
//		PLAYING("Playing"),
//		STALLED("Stalled"),
//		DROPPED("Dropped");
//
//		override fun toString(): String {
//			return status
//		}
//	}
}