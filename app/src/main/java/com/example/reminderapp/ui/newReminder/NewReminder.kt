package com.example.reminderapp.ui.newReminder




import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.reminderapp.ui.account.BackButtonTopBar


@Composable

fun NewReminder(navController: NavController) {
	Scaffold(
			modifier = Modifier.fillMaxSize(),
			topBar = { BackButtonTopBar(page = "New reminder", navController = navController ) }
			)
	{
		val name = rememberSaveable { (mutableStateOf("")) }
		val description = rememberSaveable { (mutableStateOf("")) }
		val startDate = rememberSaveable { (mutableStateOf("")) }
		val endDate = rememberSaveable { (mutableStateOf("")) }
		val startTime = rememberSaveable { (mutableStateOf("")) }
		val endTime = rememberSaveable { (mutableStateOf("")) }

		Column(
				modifier = Modifier
					.fillMaxWidth()
					.fillMaxHeight()
					.padding(40.dp),
				verticalArrangement = Arrangement.Center,
				horizontalAlignment = Alignment.CenterHorizontally

			  ) {
			OutlinedTextField(

					value = name.value,
					onValueChange = {name.value = it} ,
					modifier = Modifier.fillMaxWidth(),
					label = {Text("Reminder name")},
					shape = MaterialTheme.shapes.small,
					singleLine = true
							 )

			Spacer(modifier = Modifier.height(10.dp))

			OutlinedTextField(
					value = description.value,
					onValueChange = {description.value = it} ,
					modifier = Modifier.fillMaxWidth(),
					label = { Text("Reminder description") },
					shape = MaterialTheme.shapes.small)

			Spacer(modifier = Modifier.height(20.dp))
			Divider()
			Spacer(modifier = Modifier.height(15.dp))

			Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
				OutlinedTextField(
						value = startDate.value,
						onValueChange = {startDate.value = it} ,
						modifier = Modifier
							.fillMaxWidth()
							.weight(1f),
						label = { Text("Start date") },
						shape = MaterialTheme.shapes.small
								 )
				
				Spacer(modifier = Modifier.width(10.dp))
				
				OutlinedTextField(
						value = endDate.value,
						onValueChange = {endDate.value = it} ,
						modifier = Modifier
							.fillMaxWidth()
							.weight(1f),
						label = { Text("End date") },
						shape = MaterialTheme.shapes.small
								 )


			}

			Spacer(modifier = Modifier.height(20.dp))
			Divider()
			Spacer(modifier = Modifier.height(15.dp))

			Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
				OutlinedTextField(
						value = startTime.value,
						onValueChange = {startTime.value = it} ,
						modifier = Modifier
							.fillMaxWidth()
							.weight(1f),
						label = { Text("Start time") },
						shape = MaterialTheme.shapes.small)
				Spacer(modifier = Modifier.width(10.dp))
				OutlinedTextField(
						value = endTime.value,
						onValueChange = {endTime.value = it} ,
						modifier = Modifier
							.fillMaxWidth()
							.weight(1f),
						label = { Text("End time") },
						shape = MaterialTheme.shapes.small)
			}
			
			Spacer(modifier = Modifier.height(40.dp))
			Button(
					onClick = { navController.navigate(route = "home")},
					modifier = Modifier.fillMaxWidth().height(60.dp),
					shape = MaterialTheme.shapes.medium,
					colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant)
				  ) { Text(text = "Save") }


		}


	}
}
