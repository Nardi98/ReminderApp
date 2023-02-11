package com.example.reminderapp.data


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.*
import java.time.LocalDate
import java.time.LocalTime


@Entity(
		tableName = "reminders",
		indices = [Index("id", unique = true)]
	   )

data class Reminder(
		@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
		@ColumnInfo(name = "name")val name:String,
		@ColumnInfo(name = "description")val description: String,
		@ColumnInfo(name = "beginningDate")val beginningDate: LocalDate,
		@ColumnInfo(name = "beginningTime" )val beginningTime: Int? = null, //LocalTime?,
		@ColumnInfo(name = "endingDate")val endingDate: LocalDate? = null,
		@ColumnInfo(name = "endingTime")val endingTime: Int? = null//LocalTime?,
		//val category: Category

				   )


class LocalDateConverter {
	@RequiresApi(Build.VERSION_CODES.O)
	@TypeConverter
	fun fromTimestamp(value: Long?): LocalDate? {
		return value?.let { LocalDate.ofEpochDay(it) }
	}

	@RequiresApi(Build.VERSION_CODES.O)
	@TypeConverter
	fun dateToTimestamp(date: LocalDate?): Long? {
		return date?.toEpochDay()
	}
}

/*class LocalTimeConverter {

	@RequiresApi(Build.VERSION_CODES.O)
	@TypeConverter
	fun fromTimestamp(value: String?): LocalTime? {
		return LocalTime.parse(value)
	}

	@TypeConverter
	fun dateToTimestamp(time: LocalTime?): String? {
		return time?.toString()
	}
}*/
