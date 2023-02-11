package com.example.reminderapp.data


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.*
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId


@Entity(
		tableName = "reminders",
		indices = [Index("id", unique = true)]
	   )
@TypeConverters(LocalDateTimeConverter::class)
data class Reminder(
		@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
		@ColumnInfo(name = "message") val message: String,
		val creationTime: LocalDateTime,
		val reminderTime: LocalDateTime? = null,
		val locationX: Float? = null,
		val locationY: Float? = null,

		//val category: Category

				   )



class LocalDateTimeConverter {
	@RequiresApi(Build.VERSION_CODES.O)
	@TypeConverter
	fun fromTimestamp(value: Long?): LocalDateTime? {
		return value?.let { LocalDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneId.systemDefault()) }
	}

	@RequiresApi(Build.VERSION_CODES.O)
	@TypeConverter
	fun dateToTimestamp(date: LocalDateTime?): Long? {
		return date?.atZone(ZoneId.systemDefault())?.toInstant()?.toEpochMilli()
	}
}
