package com.example.reminderapp.ui.newReminder




import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.reminderapp.Graph
import com.example.reminderapp.data.Reminder
import com.example.reminderapp.ui.account.BackButtonTopBar
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@Composable

fun NewReminder(
		navController: NavController,

		) {
	//variables
	val reminderRepository = Graph.reminderRepository
	val coroutineScope = rememberCoroutineScope()

	//variables for inserting data
	var message by remember{ mutableStateOf("") }

	// variables for the date time pickers
	var pickedDate by remember { mutableStateOf(LocalDate.now()) }
	var pickedTime by remember { mutableStateOf(LocalTime.now()) }

	//formatted date time in order to show the date time correctly
	val formattedDate by remember{derivedStateOf{ DateTimeFormatter.ofPattern("MMM dd yyyy").format(pickedDate) }}
	val formattedTime by remember{derivedStateOf{ DateTimeFormatter.ofPattern("hh:mm").format(pickedTime) }}

	// Dialogue states to show the dialogue
	val dateDialogueState = rememberMaterialDialogState()
	val timeDialogueState = rememberMaterialDialogState()

	// scaffold in order to have the top bar
	Scaffold(
			modifier = Modifier.fillMaxSize(),
			topBar = { BackButtonTopBar(page = "New reminder", navController = navController ) }
			)
	{ padding ->

		// initial column containing everything
		Column(
				modifier = Modifier
					.fillMaxWidth()
					.fillMaxHeight()
					.padding(40.dp),
				verticalArrangement = Arrangement.Center,
				horizontalAlignment = Alignment.CenterHorizontally

			  ) {


			OutlinedTextField(
					value = message,
					onValueChange = {message = it} ,
					modifier = Modifier.fillMaxWidth(),
					label = {Text("Reminder name")},
					shape = MaterialTheme.shapes.small,
					singleLine = true
							 )

			/** Date Time pickers **/
			Spacer(modifier = Modifier.height(20.dp))
			Divider()
			Spacer(modifier = Modifier.height(20.dp))


			//row for date time picking
			Row(
					modifier = Modifier.fillMaxWidth(),
					horizontalArrangement = Arrangement.SpaceEvenly,
					verticalAlignment = Alignment.CenterVertically) {

				//Date picker
				Text(
						text = formattedDate,
						modifier = Modifier
							.fillMaxWidth()
							.weight(1f)
							.clickable { dateDialogueState.show() },

								 )
				
				Spacer(modifier = Modifier.width(10.dp))

				//Time picker
				Text(
						text = formattedTime,
						modifier = Modifier
							.fillMaxWidth()
							.weight(1f)
							.clickable { timeDialogueState.show() }

								 )
			}



			/** Confirm button **/
			Spacer(modifier = Modifier.height(40.dp))
			Button(
					//attributes of the button
					enabled = message.isNotEmpty(),
					onClick = {
						//coroutine launch to add a reminder to the database
						coroutineScope.launch {
							//calling the repository to add a reminder to the database
							reminderRepository.addReminder(
									reminder = Reminder(
											message = message,
											reminderTime = pickedDate.atTime(pickedTime),
											creationTime = LocalDateTime.now()
													   )
														  )
						}
						navController.navigate(route = "home")
							  },
					modifier = Modifier
						.fillMaxWidth()
						.height(60.dp),
					shape = MaterialTheme.shapes.medium,
					colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant)
				  )
			//content of the button
			{
				Text(text = "Save")
			}


		}
	}

	/** Date and time dialogue definition **/
	MaterialDialog( 	//Date picker
			dialogState = dateDialogueState,
			buttons = {
				positiveButton(text = "Set")
				negativeButton(text = "Cancel")
			}
				  ) {
		this.datepicker(
				initialDate = LocalDate.now(),
				title = "Set the reminder date"
					   ){
			pickedDate = it
		}
	}
	MaterialDialog( 	// time Picker
			dialogState = timeDialogueState,
			buttons = {
				positiveButton(text = "Set")
				negativeButton(text = "Cancel")
			}
				  ) {
		this.timepicker(
				initialTime = LocalTime.NOON,
				title = "Set the reminder date",
				is24HourClock = true
					   ) {
			pickedTime = it
		}


	}
}

