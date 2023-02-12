package com.example.reminderapp.ui.modifyReminder

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.reminderapp.Graph
import com.example.reminderapp.data.Reminder
import com.example.reminderapp.data.repository.ReminderRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class ModifyReminderViewModel(
		private val Id: Long,
		private val reminderRepository: ReminderRepository = Graph.reminderRepository
							 ): ViewModel() {


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

class ModifyReminderViewModelFactory(private val Id: Long) :
		ViewModelProvider.NewInstanceFactory() {
	@RequiresApi(Build.VERSION_CODES.O)
	override fun <T : ViewModel?> create(modelClass: Class<T>): T = ModifyReminderViewModel(Id) as T
}