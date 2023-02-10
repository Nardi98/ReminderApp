package com.example.reminderapp.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalTime


@RequiresApi(Build.VERSION_CODES.O)
val Reminders = listOf(
		Reminder(
				name = "Dentist",
				description="going to the dentist to clean my teeth" ,
				beginningDate = LocalDate.of(2024, 10, 12),
				beginningTime = 5,//LocalTime.of(10, 20),
				endingDate = LocalDate.of(2024, 10, 12),
				endingTime = 5 //LocalTime.of(12, 20),*/

			 ),
		Reminder(
				name = "Dentist",
				description="going to the dentist to clean my teeth" ,
				beginningDate = LocalDate.of(2024, 10, 12),
				beginningTime = 5,//LocalTime.of(10, 20),
				endingDate = LocalDate.of(2024, 10, 12),
				endingTime = 5 //LocalTime.of(12, 20),*/

				)


		)