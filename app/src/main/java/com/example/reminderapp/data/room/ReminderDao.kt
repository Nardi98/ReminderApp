package com.example.reminderapp.data.room


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

import com.example.reminderapp.data.Reminder
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ReminderDao {

	@Query(value = "SELECT * FROM reminders WHERE name = :name")
	abstract suspend fun getReminderWithName(name:String): Reminder?

	@Query(value = "SELECT * FROM reminders WHERE id = :REminderId")
	abstract suspend fun getReminderById(REminderId:Long): Reminder?

	@Query(value = "SELECT * FROM reminders LIMIT 15")
	abstract fun reminders(): Flow<List<Reminder>>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	abstract suspend fun insertAll(entities: Collection<Reminder>)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	abstract suspend fun insert(entity: Reminder):Long

	@Update(onConflict = OnConflictStrategy.REPLACE)
	abstract suspend fun  update(entity: Reminder)

	@Delete
	abstract suspend fun  delete(entity: Reminder)
}