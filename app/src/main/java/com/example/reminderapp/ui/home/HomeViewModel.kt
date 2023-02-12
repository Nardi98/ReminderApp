package com.example.reminderapp.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reminderapp.Graph
import com.example.reminderapp.Graph.reminderRepository
import com.example.reminderapp.data.Reminder
import com.example.reminderapp.data.repository.ReminderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
class HomeViewModel(
		private val reminderRepository: ReminderRepository = Graph.reminderRepository
				   ): ViewModel(){
	private val _state = MutableStateFlow(HomeViewState())

	val state: StateFlow<HomeViewState>
	get() = _state


	init {
		//loadRemindersInDb() // was needed for debugging purpose
		viewModelScope.launch {
			reminderRepository.reminders().collect( ){ remindersList ->
				_state.value = HomeViewState(
						reminders = remindersList
							 )
			}
		}


	}

	fun removeReminder(reminder: Reminder){
		viewModelScope.launch {
			Graph.reminderRepository.deleteReminder(reminder)
		}

	}

	/**this function was needed for starting the project by adding some fake functions inside the database
	 * if it will be needed again to add fake value decomment also the section in the init function
	 */


	/*@RequiresApi(Build.VERSION_CODES.O)
	private fun loadRemindersInDb(){

		print("loadReminder")
		val list = mutableListOf(
				Reminder(
						name = "dentist",
						description = "going to the dentist",
						beginningDate = LocalDate.of(2024, 10, 12),
						beginningTime = 5,//LocalTime.of(10, 20),
						endingDate = LocalDate.of(2024, 10, 12),
						endingTime = 5 //LocalTime.of(12, 20),
						),
				Reminder(
						name = "mobile computing assignment",
						description = "finish mobile computing assignment",
						beginningDate = LocalDate.of(2024, 10, 12),
						beginningTime = 5,//LocalTime.of(10, 20),
						endingDate = LocalDate.of(2024, 10, 12),
						endingTime = 5 //LocalTime.of(12, 20),
						)


								)
		viewModelScope.launch {
			list.forEach { reminder -> reminderRepository.addReminder(reminder) }
		}
	}*/
		}

data class HomeViewState(
		val reminders: List<Reminder> = emptyList()
						)