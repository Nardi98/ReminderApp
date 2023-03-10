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


		}

data class HomeViewState(
		val reminders: List<Reminder> = emptyList()
						)