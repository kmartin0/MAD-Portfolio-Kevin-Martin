package com.kevin.a07bucketlist.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.kevin.a07bucketlist.dao.BucketListDao
import com.kevin.a07bucketlist.model.BucketItem


@Database(entities = [BucketItem::class], version = 1, exportSchema = false)
abstract class BucketListRoomDatabase : RoomDatabase() {
	companion object {
		@Volatile
		private var gameRoomDatabaseInstance: BucketListRoomDatabase? = null

		fun getDatabase(context: Context): BucketListRoomDatabase? {
			if (gameRoomDatabaseInstance == null) {
				synchronized(BucketListRoomDatabase::class.java) {
					if (gameRoomDatabaseInstance == null) {
						gameRoomDatabaseInstance = Room.databaseBuilder(context.applicationContext,
								BucketListRoomDatabase::class.java, "bucket_list_database")
								.build()
					}
				}
			}
			return gameRoomDatabaseInstance
		}
	}

	abstract fun bucketListDao(): BucketListDao
}
