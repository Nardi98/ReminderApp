package com.example.reminderapp

import android.content.Context
import androidx.room.Room
import com.example.reminderapp.data.repository.ReminderRepository
import com.example.reminderapp.data.room.ReminderAppDatabase

/*
 *simple singleton dependecy graph
*/
object Graph {

	lateinit var database: ReminderAppDatabase
		private  set

	val reminderRepository by lazy {
		ReminderRepository(
				reminderDao = database.reminderDao()
						  )
	}

	fun provide(context: Context){
		database = Room.databaseBuilder(context, ReminderAppDatabase::class.java, "data.db")
			.fallbackToDestructiveMigration() //just for demo purposes
			.build()
	}
}